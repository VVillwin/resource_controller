package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.IRepositoryWrapper;

public abstract class Request implements IRequest {
    protected IRepositoryWrapper repo;
    protected IRole role;

    public IRepositoryWrapper getRepository() {
        return repo;
    }

    public void setRepository(IRepositoryWrapper repo) {
        this.repo = repo;
    }

    public IRole getRole() {
        return role;
    }

    public void setRole(IRole role) {
        this.role = role;
    }
}
