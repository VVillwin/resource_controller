package org.presentation4you.resource_controller.server.Repository;

public class FakeUserNotFoundRepo implements IUserRepo {
    public String get(final String login) {
        return null;
    }
}
