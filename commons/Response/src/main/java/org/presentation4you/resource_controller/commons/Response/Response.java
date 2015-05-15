package org.presentation4you.resource_controller.commons.Response;

public class Response implements IResponse {
    protected ResponseStatus status;

    public Response() {
        status = ResponseStatus.OK;
    }

    public ResponseStatus getStatus() {
        return status;
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
