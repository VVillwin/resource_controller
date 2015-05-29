package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.RequestsFields.ResourcesFields;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class GetResourcesReq extends Request {
    private ResourcesFields match = new ResourcesFields();

    public GetResourcesReq(final IRole role, final int id, final String type) {
        this.role = role;
        this.match.setId(id)
                  .setType(type);
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
        return true;
    }

    @Override
    public IResponse exec() {
        return super.execute(new GetResourcesRunnableReq());
    }

    private class GetResourcesRunnableReq implements IRunnableReq {
        @Override
        public IResponse run() {
            return repo.getResources(match);
        }
    }
}
