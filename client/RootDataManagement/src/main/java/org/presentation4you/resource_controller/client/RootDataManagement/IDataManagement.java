package org.presentation4you.resource_controller.client.RootDataManagement;

import org.presentation4you.resource_controller.commons.Request.IRequest;
import org.presentation4you.resource_controller.commons.Response.IResponse;

public interface IDataManagement {
    IResponse send(IRequest request);
}
