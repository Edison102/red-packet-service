package com.baixing.hc.redpacket.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author hucong
 * @date 2020/11/8 2:25 下午
 */
public class RedPacketUtil {
    /**
     * 发红包算法，二倍均值法
     *
     * @param totalAmount    红包总金额，单位为元
     * @param totalPeopleNum 总人数
     * @return
     */
    public static List<Integer> divideRedPacket(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> list = new ArrayList<>();
        /**
         * 判断总金额和总个数参数的合法性
         */
        if (totalAmount > 0 && totalPeopleNum > 0) {
            //记录剩余总金额，初始化为红包总金额
            Integer restAmount = totalAmount;
            //记录剩余总人数，初始化为指定的总人数
            Integer restPeopleNum = totalPeopleNum;
            //产生随机数
            Random random = new Random();
            //不断循环遍历，更新迭代产生随机金额，直到N-1>0
            for (int i = 0; i < totalPeopleNum - 1; i++) {
                //随机范围:[1,剩余人均金额的两倍),随机金额为分，至少一个人得到1分钱
                int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
                //更新剩余的总金额
                restAmount = restAmount - amount;
                //更新剩余的人数
                restPeopleNum--;
                //将产生的随机金额加入到list
                list.add(amount);
            }
            //循环完毕，将剩余的金额也即是最后一个随机金额，加入到list
            list.add(restAmount);
        }
        return list;
    }
}
