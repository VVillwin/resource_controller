package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.RequestsFields.RequestsFields;
import org.presentation4you.resource_controller.commons.Response.IResponse;

public class GetRequestsReq extends Request {
    private RequestsFields match;

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
