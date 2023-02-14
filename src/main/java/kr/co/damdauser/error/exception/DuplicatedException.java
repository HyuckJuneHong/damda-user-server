package kr.co.damdauser.error.exception;

import kr.co.damdauser.error.model.ErrorCode;

public class DuplicatedException extends BusinessLogicException{
    public DuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
