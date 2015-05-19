package org.presentation4you.resource_controller.commons.Role;

public abstract class Role implements IRole {
    protected String login;
    protected String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public IRole setLogin(final String login) {
        this.login = login;
        return this;
    }

    public IRole setPassword(final String password) {
        this.password = password;
        return this;
    }
}
