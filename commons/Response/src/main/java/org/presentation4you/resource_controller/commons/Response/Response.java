package org.presentation4you.resource_controller.commons.Response;

public class Response implements IResponse {
    private static final long serialVersionUID = -5362880735654636512L;

    protected ResponseStatus status;

    public Response() {
        status = ResponseStatus.INITIAL;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public boolean isOk() {
        if (status != ResponseStatus.OK) {
            return false;
        }
        return true;
    }

    @Override
    public void setIsOk() {
        status = ResponseStatus.OK;
    }

    @Override
    public void setIsNotValid() {
        status = ResponseStatus.NOT_VALID;
    }

    @Override
    public void setIsNotFound() {
        status = ResponseStatus.NOT_FOUND;
    }

    @Override
    public void setAlreadyHas() {
        status = ResponseStatus.ALREADY_HAS;
    }

    @Override
    public void setHasConflict() {
        status = ResponseStatus.HAS_CONFLICT;
    }

    @Override
    public void setRepoWrapperError() {
        status = ResponseStatus.REPOWRAPPER_ERROR;
    }
}
