package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class RemoveResourceReq extends Request {
    private int id;

    public RemoveResourceReq(IRole role, final int id) {
        this.role = role;
        this.id = id;
    }

    public boolean isValid() {
        try {
            if (!super.isValid()) {
                return false;
            }
        } catch (NullPointerException npe) {
            return false;
        }

        if ( ! role.canManageResources()) {
            return false;
        }

        return true;
    }

    public IResponse exec() {
        return super.execute(new RemoveResourceRunnableReq());
    }

    private class RemoveResourceRunnableReq implements IRunnableReq {
        @Override
        public IResponse run() {
            return repo.removeResource(id);
        }
    }
}
