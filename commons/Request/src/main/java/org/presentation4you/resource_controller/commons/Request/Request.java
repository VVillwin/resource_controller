package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.IRepository;

public abstract class Request implements IRequest {
    protected IRepository repo;
    protected IRole role;

    public IRepository getRepository() {
        return repo;
    }

    public void setRepository(IRepository repo) {
        this.repo = repo;
    }

    public IRole getRole() {
        return role;
    }

    public void setRole(IRole role) {
        this.role = role;
    }
}
