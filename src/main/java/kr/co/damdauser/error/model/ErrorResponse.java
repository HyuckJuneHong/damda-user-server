package kr.co.damdauser.error.model;

public class ErrorResponse {
    private String message;
    private int status;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public static ErrorResponse of(ErrorCode errorCode){
        return new ErrorResponse(
                errorCode.getMessage(),
                errorCode.getStatus()
        );
    }

    public static ErrorResponse of(String errorMessage){
        return new ErrorResponse(
                errorMessage,
                400
        );
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
