package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.Response.IResponse;

import java.util.Calendar;

public interface IRepositoryWrapper {
    IRepositoryWrapper setUserRepo(final IUserRepo userRepo);
    IRepositoryWrapper setResourceRepo(final IResourceRepo resourceRepo);
    IRepositoryWrapper setRequestRepo(final IRequestRepo requestRepo);
    IResourceRepo getResourceRepo();
    IResponse getUserInfo(final String login);
    IResponse addResource(final int id, final String type);
    IResponse removeResource(final int id);
    String getRequestOwner(final int id);
    IResponse addRequest(final int resourceId, final Calendar from,
                         final Calendar to, final String login);
    IResponse removeRequest(final int id);
    IResponse updateRequest(final int id, final boolean isApproved);
    IResponse loginUser(final String login, final String password);
}
