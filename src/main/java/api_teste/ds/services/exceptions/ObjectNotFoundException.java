package api_teste.ds.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message) {

        super(message);

    }
    
}
