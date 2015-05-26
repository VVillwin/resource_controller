package org.presentation4you.resource_controller.commons.Role;

public class Coordinator extends Role {
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
