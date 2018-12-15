package server.crm.responses.base;

import static server.crm.responses.base.ResponseStatus.FAILED;

public class Error {
    private int status = FAILED.value;
    private String message;

    public int getStatus() {
        return status;
    }

    public Error setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Error setMessage(String message) {
        this.message = message;
        return this;
    }
}
