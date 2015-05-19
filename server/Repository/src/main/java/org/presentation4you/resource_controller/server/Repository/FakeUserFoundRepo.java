package org.presentation4you.resource_controller.server.Repository;

public class FakeUserFoundRepo implements IUserRepo {
    private String email;

    public FakeUserFoundRepo(final String email) {
        this.email = email;
    }

    public String get(final String login) {
        return email;
    }
}
