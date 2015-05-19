package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class UpdateRequestReq extends Request {
    private int id;
    private boolean isApproved;

    public UpdateRequestReq(final IRole role, final int id, final boolean isApproved) {
        this.role = role;
        this.id = id;
        this.isApproved = isApproved;
    }

    @Override
    public boolean isValid() {
        if (! role.canUpdateRequests()) {
            return false;
        }
        return true;
    }

    public IResponse exec() {
        return super.execute(new UpdateRequestRunnableReq());
    }

    private class UpdateRequestRunnableReq implements IRunnableReq {
        @Override
        public IResponse run() {
            return repo.updateRequest(id, isApproved);
        }
    }
}
