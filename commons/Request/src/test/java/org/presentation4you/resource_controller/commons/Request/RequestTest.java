package org.presentation4you.resource_controller.commons.Request;

import org.junit.Test;
import org.presentation4you.resource_controller.commons.Response.GetUserInfoResp;
import org.presentation4you.resource_controller.commons.Role.Coordinator;
import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.FakeRepository;
import org.presentation4you.resource_controller.server.Repository.IRepository;

import static org.junit.Assert.*;

public class RequestTest {
    private IRequest request;
    private final String login = "admin";
    private final String email = "admin@ya.ru";

    @Test
    public void canInitiateWithRole() {
        IRole role = new Coordinator();

        Request request = new GetUserInfoReq(role);

        assertEquals(role, request.getRole());
    }

    @Test
    public void canSetRepository() {
        IRole role = new Coordinator();
        request = new GetUserInfoReq(role);
        IRepository repo = new FakeRepository();

        request.setRepository(repo);

        assertEquals(repo, request.getRepository());
    }

    @Test
    public void canGetUserInfo() {
        FakeRepository repo = new FakeRepository();
        repo.setLogin(login);
        repo.setEmail(email);
        IRole role = new Coordinator();
        Request request = new GetUserInfoReq(role);
        request.setRepository(repo);

        GetUserInfoResp response = (GetUserInfoResp) request.exec();

        assertEquals(login, response.getLogin());
        assertEquals(email, response.getEmail());
    }

}
