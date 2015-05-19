package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.IRole;

import java.util.Calendar;

public class AddRequestReq extends Request {
    private int resourceId;
    private Calendar from;
    private Calendar to;

    public AddRequestReq(IRole role, final int resourceId,
                         final Calendar from, final Calendar to) {
        this.role       = role;
        this.resourceId = resourceId;
        this.from       = from;
        this.to         = to;
    }

    public boolean isValid() {
        return true;
    }

    public IResponse exec() {
        return super.execute(new AddRequestRunnableReq());
    }

    private class AddRequestRunnableReq implements IRunnableReq {
        @Override
        public IResponse run() {
            return repo.addRequest(resourceId, from, to, role.getLogin());
        }
    }
}
