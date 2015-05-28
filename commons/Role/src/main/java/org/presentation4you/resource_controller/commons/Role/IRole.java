package org.presentation4you.resource_controller.commons.Role;

import java.io.Serializable;

public interface IRole extends Serializable {
    String COORDINATOR = "Coordinator";
    String EMPLOYEE    = "Employee";

    String getLogin();
    String getPassword();
    String getRoleName();
    IRole setLogin(final String login);
    IRole setPassword(final String password);
    boolean canGetUserInfo();
    boolean canManageResources();
    boolean canUpdateRequests();
}
