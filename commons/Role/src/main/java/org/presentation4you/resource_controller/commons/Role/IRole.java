package org.presentation4you.resource_controller.commons.Role;

public interface IRole {
    String COORDINATOR = "Coordinator";
    String EMPLOYEE    = "Employee";

    String getLogin();
    String getPassword();
    IRole setLogin(final String login);
    IRole setPassword(final String password);
    boolean canGetUserInfo();
    boolean canManageResources();
    boolean canUpdateRequests();
}
