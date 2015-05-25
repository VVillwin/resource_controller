package org.presentation4you.resource_controller.commons.Response;

public interface IResponse {
    ResponseStatus getStatus();
    boolean isOk();
    void setIsOk();
    void setIsNotValid();
    void setIsNotFound();
    void setAlreadyHas();
    void setHasConflict();
    void setRepoWrapperError();
}
