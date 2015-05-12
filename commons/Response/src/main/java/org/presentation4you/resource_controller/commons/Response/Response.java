package org.presentation4you.resource_controller.commons.Response;

enum ResponseStatus {
    OK("OK"),
    NOT_VALID("The request is not valid"),
    NOT_FOUND("The request has not been found"),
    ALREADY_HAS("The repository has already contained the item");

    private final String name;

    private ResponseStatus(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

public class Response implements IResponse {
    protected ResponseStatus status;

    public Response() {
        status = ResponseStatus.OK;
    }

    public boolean isOk() {
        if (status != ResponseStatus.OK) {
            return false;
        }
        return true;
    }

    public void setIsNotValid() {
        status = ResponseStatus.NOT_VALID;
    }

    public void setIsNotFound() {
        status = ResponseStatus.NOT_FOUND;
    }

    public void setAlreadyHas() {
        status = ResponseStatus.ALREADY_HAS;
    }
}
