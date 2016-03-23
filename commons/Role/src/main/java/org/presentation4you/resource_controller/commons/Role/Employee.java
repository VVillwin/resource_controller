package org.presentation4you.resource_controller.commons.Role;

public class Employee extends Role {
    private static final long serialVersionUID = 4949567776761649927L;

    public Employee() {
        setRoleName(IRole.EMPLOYEE);
    }

    @Override
    public boolean canGetUserInfo() {
        return false;
    }

    @Override
    public boolean canManageResources() {
        return false;
    }

    @Override
    public boolean canUpdateRequests() {
        return false;
    }
}
