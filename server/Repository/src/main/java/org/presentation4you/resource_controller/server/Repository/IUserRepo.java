package org.presentation4you.resource_controller.server.Repository;

public interface IUserRepo {
    String getEmail(final String login);
    String authorize(String login, String password);
}