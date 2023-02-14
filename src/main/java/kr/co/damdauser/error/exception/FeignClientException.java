package kr.co.damdauser.error.exception;

import lombok.Builder;

public class FeignClientException extends RuntimeException{
    private int status;
    private String methodName;

    @Builder
    public FeignClientException(String message,
                                int status,
                                String methodName){
        super(message);
        this.status = status;
        this.methodName = methodName;
    }
}

