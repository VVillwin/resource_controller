package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class GetUserInfoReq extends Request {
    private String login;

    public GetUserInfoReq(IRole role, final String login) {
        this.role = role;
        this.login = login;
    }

    public boolean isValid() {
        if ( ! role.canGetUserInfo()) {
            return false;
        }
        return true;
    }

    public IResponse exec() {
        return super.execute(new GetUserInfoRunnableReq());
    }

    private class GetUserInfoRunnableReq implements IRunnableReq {
        @Override
        public IResponse run() {
            return repo.getUserInfo(login);
        }
    }
}
