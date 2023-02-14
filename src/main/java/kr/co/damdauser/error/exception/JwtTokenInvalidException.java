package kr.co.damdauser.error.exception;

import kr.co.damdauser.error.model.ErrorCode;

public class JwtTokenInvalidException extends UserDefineException{
    public JwtTokenInvalidException(ErrorCode errorCode){
        super(errorCode);
    }
}