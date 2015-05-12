package org.presentation4you.resource_controller.commons.Response;

public class Response implements IResponse {
    protected String errorMessage;
    protected int    status;

    Response() {
        status = 0;
    }

    public boolean isOk() {
        if (status != 0) {
            return false;
        }
        return true;
    }

    public void setIsNotValid() {
        errorMessage = "notValid";
        status = 1;
    }
}
