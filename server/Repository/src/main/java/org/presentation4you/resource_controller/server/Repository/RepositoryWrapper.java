package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.Response.*;

import java.util.Calendar;
import java.util.NoSuchElementException;

import static org.presentation4you.resource_controller.commons.Response.LoginUserResp.buildLoginUserResp;

public class RepositoryWrapper implements IRepositoryWrapper {
    private IUserRepo userRepo;
    private IRequestRepo requestRepo;
    private IResourceRepo resourceRepo;

    @Override
    public IRepositoryWrapper setUserRepo(final IUserRepo userRepo) {
        this.userRepo = userRepo;
        return this;
    }

    @Override
    public IRepositoryWrapper setResourceRepo(final IResourceRepo resourceRepo) {
        this.resourceRepo = resourceRepo;
        return this;
    }

    @Override
    public IRepositoryWrapper setRequestRepo(final IRequestRepo requestRepo) {
        this.requestRepo = requestRepo;
        return this;
    }

    @Override
    public IResourceRepo getResourceRepo() {
        return resourceRepo;
    }

    @Override
    public IResponse getUserInfo(final String login) {
        if (userRepo == null) {
            throw new NullPointerException();
        }

        GetUserInfoResp response = new GetUserInfoResp();
        String email = userRepo.getEmail(login);
        if (email == null) {
            response.setIsNotFound();
        } else {
            response.setIsOk();
            response.setLogin(login);
            response.setEmail(email);
        }
        return response;
    }

    @Override
    public IResponse addResource(final int id, final String type) {
        if (resourceRepo == null) {
            throw new NullPointerException();
        }

        IResponse response = new Response();
        int typeId = 0;
        try {
            typeId = resourceRepo.getType(type);
        } catch (NoSuchElementException nse) {
            response.setIsNotFound();
            return response;
        }

        if (resourceRepo.has(id)) {
            response.setAlreadyHas();
            return response;
        }
        resourceRepo.add(id, typeId);
        response.setIsOk();
        return response;
    }

    @Override
    public IResponse removeResource(final int id) {
        if (resourceRepo == null) {
            throw new NullPointerException();
        }

        IResponse response = new Response();
        if (resourceRepo.has(id)) {
            resourceRepo.remove(id);
            response.setIsOk();
            return response;
        }

        response.setIsNotFound();
        return response;
    }

    @Override
    public IResponse addRequest(final int resourceId, final Calendar from,
                                final Calendar to, final String login) {
        if (requestRepo == null) {
            throw new NullPointerException();
        }

        IResponse response = new Response();
        try {
             if (requestRepo.canAdd(resourceId, from, to, login)) {
                 int id = requestRepo.add(resourceId, from, to, login);
                 response = new AddRequestResp(id);
                 response.setIsOk();
             } else {
                 response.setAlreadyHas();
             }
        } catch (IllegalArgumentException iae) {
            response.setHasConflict();
        }

        return response;
    }

    @Override
    public IResponse removeRequest(final int id) {
        if (requestRepo == null) {
            throw new NullPointerException();
        }

        IResponse response = new Response();
        if (requestRepo.has(id)) {
            requestRepo.remove(id);
            response.setIsOk();
            return response;
        }

        response.setIsNotFound();
        return response;
    }

    @Override
    public IResponse updateRequest(final int id, final boolean isApproved) {
        if (requestRepo == null) {
            throw new NullPointerException();
        }

        IResponse response = new Response();
        if (requestRepo.has(id)) {
            requestRepo.update(id, isApproved);
            response.setIsOk();
            return response;
        }

        response.setIsNotFound();
        return response;
    }

    @Override
    public IResponse loginUser(final String login, final String password) {
        if (userRepo == null) {
            throw new NullPointerException();
        }

        String role = userRepo.authorize(login, password);
        IResponse response = buildLoginUserResp(login, password, role);
        return response;
    }

    @Override
    public String getRequestOwner(final int id) {
        if (requestRepo == null) {
            throw new NullPointerException();
        }

        return requestRepo.getRequestOwner(id);
    }
}
