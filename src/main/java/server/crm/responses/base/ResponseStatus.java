package server.crm.responses.base;

public enum ResponseStatus {
    FAILED(407);//unknown error

    public int value;

    ResponseStatus(int value) {
        this.value = value;
    }

    public ResponseStatus getStatus(int value) {
        for (ResponseStatus temp : ResponseStatus.values()) {
            if (value == temp.value) {
                return temp;
            }
        }
        return FAILED;
    }
}