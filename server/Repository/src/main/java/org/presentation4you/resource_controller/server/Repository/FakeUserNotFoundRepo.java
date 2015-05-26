package org.presentation4you.resource_controller.server.Repository;

public class FakeUserNotFoundRepo implements IUserRepo {
    @Override
    public String getEmail(final String login) {
        return null;
    }

    @Override
    public String authorize(String login, String password) {
        if (login.equals("admin")) {
            return "Coordinator";
        }
        return null;
    }
}
