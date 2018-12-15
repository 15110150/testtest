package server.crm.exceptions;

public class ApiRuntimeException extends RuntimeException {
    protected boolean isSuccessful;
    protected String resultCode;
    protected String resultMessage;

    public ApiRuntimeException(Throwable cause) {
        super(cause);
    }

    public ApiRuntimeException(String errorType) {
        super(errorType);

        isSuccessful = false;
        resultCode = errorType;
        resultMessage = errorType;
    }




    public ApiRuntimeException(String errorType, Throwable cause) {
        super(cause);

        isSuccessful = false;
        resultCode = errorType;
        resultMessage = errorType;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}