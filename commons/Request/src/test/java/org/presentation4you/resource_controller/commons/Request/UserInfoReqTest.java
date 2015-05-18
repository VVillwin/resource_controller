package org.presentation4you.resource_controller.commons.Request;

import org.junit.After;
import org.junit.Test;
import org.presentation4you.resource_controller.commons.Response.GetUserInfoResp;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.Coordinator;
import org.presentation4you.resource_controller.commons.Role.Employee;
import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.FakeUserFoundRepo;
import org.presentation4you.resource_controller.server.Repository.FakeUserNotFoundRepo;
import org.presentation4you.resource_controller.server.Repository.IRepositoryWrapper;
import org.presentation4you.resource_controller.server.Repository.RepositoryWrapper;

import static org.junit.Assert.assertEquals;

public class UserInfoReqTest {
    private IRepositoryWrapper repo;
    private final String login = "admin";
    private final String email = "admin@ya.ru";

    @After
    public void removeFakeRepository() {
        repo = null;
    }

    @Test
    public void canInitiateWithRole() {
        IRole role = new Coordinator();

        Request request = new GetUserInfoReq(role, login);

        assertEquals(role, request.getRole());
    }

    @Test
    public void canSetRepository() {
        IRequest request = new GetUserInfoReq(new Coordinator(), login);
        IRepositoryWrapper repo = new RepositoryWrapper();

        request.setRepository(repo);

        assertEquals(repo, request.getRepository());
    }

    @Test
    public void canGetUserInfo() {
        repo = new RepositoryWrapper().setUserRepo(new FakeUserFoundRepo(email));
        Request request = new GetUserInfoReq(new Coordinator(), login);
        request.setRepository(repo);

        GetUserInfoResp response = (GetUserInfoResp) request.exec();

        assertEquals(login, response.getLogin());
        assertEquals(email, response.getEmail());
    }

    @Test
    public void canReturnNotFoundIfUserDoesNotExist() {
        repo = new RepositoryWrapper().setUserRepo(new FakeUserNotFoundRepo());
        Request request = new GetUserInfoReq(new Coordinator(), login);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnNotValidForGetUserInfoReqIfRoleIsEmployee() {
        repo = new RepositoryWrapper().setUserRepo(new FakeUserFoundRepo(email));
        Request request = new GetUserInfoReq(new Employee(), login);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_VALID);
    }
}
