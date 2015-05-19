package org.presentation4you.resource_controller.server.Repository;

public class FakeCoordinatorFoundRepo implements IUserRepo {
    @Override
    public String getEmail(String login) {
        return null;
    }

    @Override
    public String authorize(String login, String password) {
        return "Coordinator";
    }
}
