package org.presentation4you.resource_controller.client.Authentication;

import org.presentation4you.resource_controller.client.RootDataManagement.DataManagement;
import org.presentation4you.resource_controller.commons.Request.IRequest;
import org.presentation4you.resource_controller.commons.Request.LoginUserReq;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.LoginUserResp;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class Authentication {
    private static Authentication instance;
    private IRole role;
    private DataManagement dm = new DataManagement();
    private Authentication() {
    }

    public ResponseStatus login(String login, String password) {
        IRequest request = new LoginUserReq(login, password);
        IResponse response = dm.send(request);
        if (response instanceof LoginUserResp) {
            if (((LoginUserResp) response).getRole() != null) {
                this.role = ((LoginUserResp) response).getRole();
            }
        }
        return response.getStatus();
    }

    public IRole getRole() {
        return role;
    }

    public static Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }
}
