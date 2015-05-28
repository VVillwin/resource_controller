package org.presentation4you.resource_controller.commons.Response;

import java.io.Serializable;

public interface IResponse extends Serializable {
    ResponseStatus getStatus();
    boolean isOk();
    void setIsOk();
    void setIsNotValid();
    void setIsNotFound();
    void setAlreadyHas();
    void setHasConflict();
    void setRepoWrapperError();
}
