package miw.restControllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundUserIdException.class})
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception, request.getRequestURI().toString());
        return errorMessage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class, MalformedHeaderException.class}) // Exception no esperada--> BAD-REQUEST
    @ResponseBody
    public ErrorMessage badRequest(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception, "");
        return errorMessage;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({org.springframework.security.access.AccessDeniedException.class})
    @ResponseBody
    public ErrorMessage forbiddenRequest(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception, "");
        return errorMessage;
    }
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    public void conflictRequest(Exception exception) {
    }


}
