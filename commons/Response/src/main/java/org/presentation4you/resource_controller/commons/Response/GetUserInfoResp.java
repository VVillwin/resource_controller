package org.presentation4you.resource_controller.commons.Response;

public class GetUserInfoResp extends Response {
    private String login;
    private String email;

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
