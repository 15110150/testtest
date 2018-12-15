package server.crm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : trungntm
 * @date : 23/10/2018 22h30
 * @content : add custom exception with HttpStatus.NOT_FOUND
 * */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message){
        super(message);
    }
}
