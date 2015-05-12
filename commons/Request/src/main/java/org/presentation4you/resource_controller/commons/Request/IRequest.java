package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.server.Repository.IRepository;

public interface IRequest {
    boolean isValid();
    IRepository getRepository();
    void    setRepository(IRepository repo);
    IResponse exec();
}
