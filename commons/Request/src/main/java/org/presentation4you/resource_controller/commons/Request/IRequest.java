package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.server.Repository.IRepositoryWrapper;

import java.io.Serializable;

public interface IRequest  extends Serializable {
    boolean isValid();
    IRepositoryWrapper getRepository();
    void    setRepository(IRepositoryWrapper repo);
    IResponse exec();
}
