package org.presentation4you.resource_controller.commons.Request;

import org.presentation4you.resource_controller.commons.Response.IResponse;

public class LoginUserReq extends Request {
    private String login;
    private String password;

    public LoginUserReq(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public IResponse exec() {
        return super.execute(new LoginUserRunnableReq());
    }

    private class LoginUserRunnableReq implements IRunnableReq {
        @Override
        public IResponse run() {
            return repo.loginUser(login, password);
        }
    }
}
