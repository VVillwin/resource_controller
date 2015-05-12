package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.Response.GetUserInfoResp;
import org.presentation4you.resource_controller.commons.Response.IResponse;

public class FakeRepository implements IRepository {
    private String login;
    private String email;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public IResponse getUserInfo() {
        GetUserInfoResp response = new GetUserInfoResp();
        response.setLogin(login);
        response.setEmail(email);

        return response;
    }
}
