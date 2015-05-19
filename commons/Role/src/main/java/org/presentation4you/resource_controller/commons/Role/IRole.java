package org.presentation4you.resource_controller.commons.Role;

public interface IRole {
    String getLogin();
    String getPassword();
    IRole setLogin(final String login);
    boolean canGetUserInfo();
    boolean canManageResources();
    boolean canUpdateRequests();
}
