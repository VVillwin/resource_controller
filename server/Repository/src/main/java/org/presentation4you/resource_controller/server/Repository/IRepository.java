package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.Response.IResponse;

public interface IRepository {
    IResponse getUserInfo(final String login);
    IResponse addResource(int id, String type);
    IResponse removeResource(int id);
}