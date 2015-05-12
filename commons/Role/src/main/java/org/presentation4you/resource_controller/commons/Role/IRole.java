package org.presentation4you.resource_controller.commons.Role;

public interface IRole {
    String getLogin();
    String getPassword();
    boolean canGetUserInfo();
    boolean canManageResources();
}
