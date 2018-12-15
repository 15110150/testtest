package server.crm.responses.base;

import java.util.ArrayList;
import java.util.List;

public class BaseResponse {
    private List<Error> errors = new ArrayList<>();
    private Object result;

    public List<Error> getErrors() {
        return errors;
    }

    public BaseResponse setErrors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public BaseResponse setResult(Object result) {
        this.result = result;
        return this;
    }


}
