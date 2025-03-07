package es.upm.api.infrastructure.resources.httperrors;

import es.upm.api.domain.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ApiExceptionHandler {


    private final Environment environment;

    @Autowired
    public ApiExceptionHandler(Environment environment) {
        this.environment = environment;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            org.springframework.security.access.AccessDeniedException.class
    })
    @ResponseBody
    public void unauthorizedRequest(Exception exception) {
        LogManager.getLogger(this.getClass()).debug(() -> "Unauthorized: " + exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NoResourceFoundException.class,
            ResponseStatusException.class

    })
    @ResponseBody
    public ErrorMessage noResourceFoundRequest(Exception exception) {
        return new ErrorMessage(new NotFoundException(
                "Ruta no encontrada. Prueba con: **/actuator/info o **/swagger-ui.html o **/v3/api-docs"),
                HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(Exception exception) {
        return new ErrorMessage(exception, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.bind.support.WebExchangeBindException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(Exception exception) {
        return new ErrorMessage(exception, HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            ConflictException.class
    })
    @ResponseBody
    public ErrorMessage conflict(Exception exception) {
        return new ErrorMessage(exception, HttpStatus.CONFLICT.value());
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            ForbiddenException.class
    })
    @ResponseBody
    public ErrorMessage forbidden(Exception exception) {
        return new ErrorMessage(exception, HttpStatus.FORBIDDEN.value());
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({
            BadGatewayException.class
    })
    @ResponseBody
    public ErrorMessage badGateway(Exception exception) {
        return new ErrorMessage(exception, HttpStatus.BAD_GATEWAY.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ErrorMessage exception(Exception exception) { //WARNING!!!. It is caught for unforeseen cases.The error must be properly handled or caught.
        if (environment.acceptsProfiles(Profiles.of("dev", "test"))) {
            exception.printStackTrace();
        }
        return new ErrorMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
