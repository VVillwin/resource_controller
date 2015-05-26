package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.RequestsFields.RequestsFields;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class GetRequestsReq extends Request {
    private RequestsFields match;

    public GetRequestsReq(final IRole role, RequestsFields match) {
        this.role = role;
        this.match = match;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public IResponse exec() {
        return super.execute(new GetRequestsRunnableReq());
    }

    private class GetRequestsRunnableReq implements IRunnableReq {
        @Override
        public IResponse run() {
            return repo.getRequests(match);
        }
    }
}
