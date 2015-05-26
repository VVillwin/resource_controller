package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class RemoveRequestReq extends Request {
    private int id;

    public RemoveRequestReq(final IRole role, final int id) {
        this.role = role;
        this.id = id;
    }

    @Override
    public boolean isValid() {
        try {
            if (!super.isValid()) {
                return false;
            }
        } catch (NullPointerException npe) {
            return false;
        }

        return role.getLogin().equals(repo.getRequestOwner(id));
    }

    public IResponse exec() {
        return super.execute(new RemoveRequestRunnableReq());
    }

    private class RemoveRequestRunnableReq implements IRunnableReq {
        @Override
        public IResponse run() {
            return repo.removeRequest(id);
        }
    }
}
