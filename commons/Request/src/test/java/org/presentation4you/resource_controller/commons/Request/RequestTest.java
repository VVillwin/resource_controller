package org.presentation4you.resource_controller.commons.Request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.presentation4you.resource_controller.commons.Response.GetUserInfoResp;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.Coordinator;
import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.FakeRepository;
import org.presentation4you.resource_controller.server.Repository.IRepository;

import static org.junit.Assert.*;

public class RequestTest {
    private FakeRepository repo;
    private final String login = "admin";
    private final String email = "admin@ya.ru";

    @Before
    public void createFakeRepository() {
        repo = new FakeRepository();
    }

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
        IRole role = new Coordinator();
        IRequest request = new GetUserInfoReq(role, login);
        IRepository repo = new FakeRepository();

        request.setRepository(repo);

        assertEquals(repo, request.getRepository());
    }

    @Test
    public void canGetUserInfo() {
        repo.setLogin(login);
        repo.setEmail(email);
        Request request = new GetUserInfoReq(new Coordinator(), login);
        request.setRepository(repo);

        GetUserInfoResp response = (GetUserInfoResp) request.exec();

        assertEquals(login, response.getLogin());
        assertEquals(email, response.getEmail());
    }

    @Test
    public void canReturnNotFoundIfUserDoesNotExist() {
        repo.setLogin("admin1");
        repo.setEmail("admin1@ya.ru");
        Request request = new GetUserInfoReq(new Coordinator(), login);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canAddResource() {
        int id = 1;
        Request request = createAddResourceReq(id, "projector");

        request.exec();

        assertTrue(repo.doesContainResource(id));
    }

    @Test
    public void canReturnOkIfResourceHasBeenAdded() {
        Request request = createAddResourceReq(1, "projector");

        IResponse response = request.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canReturnAlreadyHasIfResourceHasNotBeenAdded() {
        Request request = createAddResourceReq(1, "projector");
        IResponse response = request.exec();

        response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.ALREADY_HAS);
    }

    @Test
    public void canRemoveResource() {
        int id = 1;
        Request addRequest = createAddResourceReq(id, "projector");
        addRequest.exec();
        Request removeRequest = new RemoveResourceReq(addRequest.getRole(), id);
        removeRequest.setRepository(repo);

        removeRequest.exec();

        assertFalse(repo.doesContainResource(id));
    }

    @Test
    public void canReturnOkIfResourceHasBeenRemoved() {
        int id = 1;
        Request addRequest = createAddResourceReq(id, "projector");
        addRequest.exec();
        Request removeRequest = new RemoveResourceReq(addRequest.getRole(), id);
        removeRequest.setRepository(repo);

        IResponse response = removeRequest.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canReturnNotFoundIfResourceHasNotBeenRemoved() {
        Request request = new RemoveResourceReq(new Coordinator(), 1);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    private Request createAddResourceReq(int id, String type) {
        IRole role = new Coordinator();

        Request request = new AddResourceReq(role, id);
        request.setRepository(repo);

        return request;
    }
}
