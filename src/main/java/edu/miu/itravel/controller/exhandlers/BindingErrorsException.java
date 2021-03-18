package edu.miu.itravel.controller.exhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

//Indicates invalid data sent with request.
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class BindingErrorsException extends RuntimeException {
    private static final Logger log = LoggerFactory.getLogger(BindingErrorsException.class);
    private static final long serialVersionUID = -7882202987868263849L;

    private String message;
    private final BindingResult bindingResult;

    public BindingErrorsException(final BindingResult bindingResult){
        this.bindingResult = bindingResult;
        initMessage();
    }

    public BindingErrorsException(
            final String message,
            final BindingResult bindingResult
    ) {
        super(message);
        this.bindingResult = bindingResult;
        log.error(getLocalizedMessage());
    }


    public BindingResult getBindingResult() {
        return bindingResult;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void initMessage() {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder messageBuilder = new StringBuilder();
        for( FieldError fieldError : fieldErrors){
            messageBuilder.append(fieldError.getField())
                    .append(" : ")
                    .append(fieldError.getDefaultMessage())
                    .append(System.getProperty("line.separator"));
        }

        message = messageBuilder.toString();
    }
}