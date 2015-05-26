package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.Response;
import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.IRepositoryWrapper;

interface IRunnableReq {
    public IResponse run();
}


public abstract class Request implements IRequest {
    protected IRepositoryWrapper repo;
    protected IRole role;

    @Override
    public boolean isValid() throws NullPointerException {
        String group = repo.verifyCredentials(role.getLogin(), role.getPassword());
        if (group.equals(role.getRoleName())) {
            return true;
        }
        return false;
    }

    @Override
    public IRepositoryWrapper getRepository() {
        return repo;
    }

    @Override
    public void setRepository(IRepositoryWrapper repo) {
        this.repo = repo;
    }

    public IRole getRole() {
        return role;
    }

    public void setRole(IRole role) {
        this.role = role;
    }

    protected IResponse execute(IRunnableReq r) {
        IResponse response;
        if (!isValid()) {
            response = new Response();
            response.setIsNotValid();
            return response;
        }
        try {
            response = r.run();
        } catch (NullPointerException npe) {
            response = new Response();
            response.setRepoWrapperError();
        }

        return response;
    }
}
