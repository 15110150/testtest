package server.crm.core.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import server.crm.core.exception.NotFoundException;

@RestController
@RequestMapping("/api/test")
public class TestApi {

    @GetMapping("success")
    @ResponseStatus(code = HttpStatus.OK)
    public String getSuccess(){
        return "success";
    }

    @GetMapping("notfound")
    public void getNotFound() throws NotFoundException {
        throw new NotFoundException("Resource not found test");

    }

}
