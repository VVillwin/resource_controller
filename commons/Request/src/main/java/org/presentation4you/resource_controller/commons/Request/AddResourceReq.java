package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class AddResourceReq extends Request {
    private int id;
    private String type;

    public AddResourceReq(IRole role, final int id) {
        this.role = role;
        this.id = id;
    }

    public boolean isValid() {
        if ( ! role.canManageResources()) {
            return false;
        }

        return true;
    }

    public IResponse exec() {
        return super.execute(new AddResourceRunnableReq());
    }

    private class AddResourceRunnableReq implements IRunnableReq {
        @Override
        public IResponse run() {
            return repo.addResource(id, type);
        }
    }
}
