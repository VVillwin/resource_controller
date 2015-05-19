package org.presentation4you.resource_controller.commons.Response;

import org.presentation4you.resource_controller.commons.Role.Coordinator;
import org.presentation4you.resource_controller.commons.Role.Employee;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class LoginUserResp extends Response {
    private IRole role;

    public static IResponse buildLoginUserResp(final String login, final String password,
                                               final String role) {
        if (role == IRole.COORDINATOR) {
            return new LoginUserResp(new Coordinator().setLogin(login)
                    .setPassword(password));
        } else if (role == IRole.EMPLOYEE) {
            return new LoginUserResp(new Employee().setLogin(login)
                    .setPassword(password));
        } else if (role == null) {
            return new LoginUserResp();
        }
        return null;
    }

    public IRole getRole() {
        return role;
    }

    private LoginUserResp() {
        setIsNotFound();
    }

    private LoginUserResp(final IRole role) {
        this.role = role;
    }
}
