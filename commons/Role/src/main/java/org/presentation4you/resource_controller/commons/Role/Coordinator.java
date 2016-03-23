package org.presentation4you.resource_controller.commons.Role;

public class Coordinator extends Role {
    private static final long serialVersionUID = 1612542343568718380L;

    public Coordinator() {
        setRoleName(IRole.COORDINATOR);
    }

    @Override
    public boolean canGetUserInfo() {
        return true;
    }

    @Override
    public boolean canManageResources() {
        return true;
    }

    @Override
    public boolean canUpdateRequests() {
        return true;
    }
}
