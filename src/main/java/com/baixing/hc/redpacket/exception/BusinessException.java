package com.baixing.hc.redpacket.exception;

import com.baixing.ms.springtime.modules.base.exception.BadRequestException;

public class BusinessException extends BadRequestException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

}
