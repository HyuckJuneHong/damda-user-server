package kr.co.damdauser.error.exception;

import kr.co.damdauser.error.model.ErrorCode;

public class BadRequestException extends BusinessLogicException{

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(String message) {
        super(String.format("Bad Request : %s", message));
    }
}
