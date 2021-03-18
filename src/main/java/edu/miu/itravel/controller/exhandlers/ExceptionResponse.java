package edu.miu.itravel.controller.exhandlers;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

    private String errorMessage;
    private String requestedURI;
    private int errorStatus;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestedURI() {
        return requestedURI;
    }

    public void callerURL(final String requestedURI) {
        this.requestedURI = requestedURI;
    }

    public int getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(HttpStatus errorStatus) {
        this.errorStatus = errorStatus.value();
    }
}