package org.presentation4you.resource_controller.commons.Role;

public abstract class Role implements IRole {
    protected String login;
    protected String password;
    protected String roleName;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public IRole setLogin(final String login) {
        this.login = login;
        return this;
    }

    @Override
    public IRole setPassword(final String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getRoleName() {
        return roleName;
    }

    protected IRole setRoleName(final String roleName) {
        this.roleName = roleName;
        return this;
    }
}
