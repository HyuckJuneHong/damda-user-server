package kr.co.damdauser.error.exception;

import kr.co.damdauser.error.model.ErrorCode;

public class JwtTokenExpiredException extends BusinessLogicException{
    public JwtTokenExpiredException(ErrorCode errorCode) {
        super(errorCode);
    }
}
