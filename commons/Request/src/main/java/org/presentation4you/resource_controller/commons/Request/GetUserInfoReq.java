package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.GetUserInfoResp;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class GetUserInfoReq extends Request {
    private String login;

    public GetUserInfoReq(IRole role) {
        this.role = role;
    }

    public IResponse exec() {
        if (! isValid()) {
            IResponse response = new GetUserInfoResp();
            response.setIsNotValid();
            return response;
        }
        return repo.getUserInfo();
    }

    public boolean isValid() {
        if (role.canGetUserInfo()) {
            return true;
        }
        return false;
    }
}
