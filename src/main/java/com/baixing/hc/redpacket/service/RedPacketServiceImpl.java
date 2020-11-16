package com.baixing.hc.redpacket.service;

import com.baixing.hc.redpacket.cache.RedPacketDetailCache;
import com.baixing.hc.redpacket.controller.vo.dto.RedPacketAmountDTO;
import com.baixing.hc.redpacket.controller.vo.dto.RedPacketDetailDTO;
import com.baixing.hc.redpacket.controller.vo.dto.RedPacketIdentityDTO;
import com.baixing.hc.redpacket.controller.vo.req.CreateRedPacketReq;
import com.baixing.hc.redpacket.controller.vo.req.GrabRedPacketReq;
import com.baixing.hc.redpacket.dao.*;
import com.baixing.hc.redpacket.exception.BusinessException;
import com.baixing.hc.redpacket.model.domain.RedPacket;
import com.baixing.hc.redpacket.model.domain.RedPacketRecord;
import com.baixing.hc.redpacket.model.vo.RedPacketMeta;
import com.baixing.hc.redpacket.utils.IdentityUtil;
import com.baixing.hc.redpacket.utils.RedPacketUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author hucong
 * @date 2020/11/8 2:49 下午
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RedPacketServiceImpl implements RedPacketService {

    private final RedPacketHistoryDao redPacketHistoryDao;
    private final RedPacketDetailCache redPacketDetailCache;
    private final RedPacketActiveDao redPacketActiveDao;
    private final RedPacketRecordDao redPacketRecordDao;
    private final RedPacketAmountDao redPacketAmountDao;
    private final RedPacketDao redPacketDao;
    private final RepealUserDao repealUserDao;

    @Override
    public RedPacketIdentityDTO createRedPacket(CreateRedPacketReq createRedPacketReq) {
        String user = createRedPacketReq.getUser();
        Integer total = createRedPacketReq.getTotal();
        Integer amount = createRedPacketReq.getAmount();
        log.info("{}准备发{}个红包，总金额：{}元", user, total, amount);

        //生成红包id
        Long redPacketId = IdentityUtil.getIdentity();

        //红包金额拆散放入队列
        List<Integer> miniAmountList = RedPacketUtil.divideRedPacket(amount, total);
        miniAmountList.forEach(miniAmount -> {
            redPacketAmountDao.insert(redPacketId, miniAmount);
        });

        //记录发的红包信息
        RedPacket redPacket = new RedPacket();
        redPacket.setId(redPacketId);
        redPacket.setUser(createRedPacketReq.getUser());
        redPacket.setAmount(createRedPacketReq.getAmount());
        redPacket.setTotal(createRedPacketReq.getTotal());
        redPacket.setCreateTime(new Date());
        redPacketDao.insert(redPacket);

        //可抢集合放入
        redPacketActiveDao.insert(redPacketId);

        return new RedPacketIdentityDTO(redPacketId);
    }

    @Override
    public RedPacketAmountDTO grabRedPacket(GrabRedPacketReq grabRedPacketReq) {
        RedPacket redPacket = redPacketDao.get(grabRedPacketReq.getRedPacketId());
        if (redPacket == null) {
            throw new BusinessException("该红包不存在");
        }
        //如果用户放入失败，说明重复
        Boolean success = repealUserDao.insert(grabRedPacketReq.getRedPacketId(), grabRedPacketReq.getUser());
        if (!success) {
            throw new BusinessException("您已获取过该红包");
        }

        Integer grabAmount = redPacketAmountDao.grab(grabRedPacketReq.getRedPacketId());
        if (grabAmount == null) {
            //如果红包还显示可抢，则删除
            if (redPacketActiveDao.isMember(grabRedPacketReq.getRedPacketId())) {
                Boolean deleted = redPacketActiveDao.delete(grabRedPacketReq.getRedPacketId());
                //如果确实成功删除，则在历史记录中加一条
                if (deleted) {
                    redPacketHistoryDao.insert(grabRedPacketReq.getRedPacketId());
                }
            }
            //删除未抢到的用户记录
            repealUserDao.delete(grabRedPacketReq.getRedPacketId(), grabRedPacketReq.getUser());
            throw new BusinessException("手慢了，该红包已抢完");
        }

        //记录单条获取记录
        RedPacketRecord record = new RedPacketRecord();
        record.setUser(grabRedPacketReq.getUser());
        record.setAmount(grabAmount);
        record.setTime(new Date());
        redPacketRecordDao.insert(grabRedPacketReq.getRedPacketId(), record);

        return new RedPacketAmountDTO(grabAmount);
    }

    @Override
    public List<RedPacketMeta> getRedPacketActiveList() {
        List<RedPacketMeta> redPacketMetaList = new ArrayList<>();

        Set<Long> activeIds = redPacketActiveDao.getAll();
        if (CollectionUtils.isEmpty(activeIds)) {
            return redPacketMetaList;
        }
        activeIds.forEach(id -> {
            RedPacket redPacket = redPacketDao.get(id);
            if (redPacket != null) {
                RedPacketMeta redPacketMeta = new RedPacketMeta();
                redPacketMeta.setUser(redPacket.getUser());
                redPacketMeta.setRedPacketId(id);
                redPacketMeta.setCreateTime(redPacket.getCreateTime());

                redPacketMetaList.add(redPacketMeta);
            }
        });

        return redPacketMetaList;
    }

    @Override
    public List<RedPacketMeta> getRedPacketHistoryList() {
        List<RedPacketMeta> historyRedPacketList = new ArrayList<>();

        List<Long> historyIds = redPacketHistoryDao.getAll();
        if (CollectionUtils.isEmpty(historyIds)) {
            return historyRedPacketList;
        }
        historyIds.forEach(id -> {
            RedPacket redPacket = redPacketDao.get(id);
            if (redPacket != null) {
                RedPacketMeta redPacketMeta = new RedPacketMeta();
                redPacketMeta.setUser(redPacket.getUser());
                redPacketMeta.setRedPacketId(id);
                redPacketMeta.setCreateTime(redPacket.getCreateTime());

                historyRedPacketList.add(redPacketMeta);
            }
        });

        return historyRedPacketList;
    }

    @Override
    public RedPacketDetailDTO getRedpacketDetail(Long id) {
        RedPacket redPacket = redPacketDao.get(id);
        if (redPacket == null) {
            throw new BusinessException("该红包不存在，查询错误");
        }
        //先查缓存
        if (redPacketDetailCache.get(id) != null) {
            return redPacketDetailCache.get(id);
        }

        //抢到的红包记录
        List<RedPacketRecord> redPacketRecordList = redPacketRecordDao.getAll(id);
        int remainingNum = redPacket.getTotal() - redPacketRecordList.size();
        int remainingAmount = redPacket.getAmount();
        for (RedPacketRecord record : redPacketRecordList) {
            remainingAmount -= record.getAmount();
        }

        RedPacketDetailDTO redPacketDetailDTO = new RedPacketDetailDTO();
        redPacketDetailDTO.setRemainingNum(remainingNum);
        redPacketDetailDTO.setRemainingAmount(remainingAmount);
        redPacketDetailDTO.setRedPacket(redPacket);
        redPacketDetailDTO.setRecords(redPacketRecordList);

        //已经抢完了就计入缓存中
        if (remainingNum == 0) {
            redPacketDetailCache.set(id, redPacketDetailDTO);
        }

        return redPacketDetailDTO;
    }
}
