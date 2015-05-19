package org.presentation4you.resource_controller.server.Repository;

public class FakeUserFoundRepo implements IUserRepo {
    private String email;

    public FakeUserFoundRepo(final String email) {
        this.email = email;
    }

    @Override
    public String getEmail(final String login) {
        return email;
    }

    @Override
    public String authorize(String login, String password) {
        return "Employee";
    }
}
