package org.presentation4you.resource_controller.commons.Request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.Coordinator;
import org.presentation4you.resource_controller.commons.Role.Employee;
import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FakeResourceReqTest {
    private static final String PROJECTOR = "Projector";
    private IRole employee = new Employee().setLogin("user")
            .setPassword("user");
    private IRole defaultRole = new Coordinator().setLogin("admin").setPassword("admin");
    private IRepositoryWrapper repo;

    @Before
    public void createFakeRepository() {
        repo = new RepositoryWrapper();
    }

    @After
    public void removeFakeRepository() {
        repo = null;
    }

    @Test
    public void canReturnOkIfResourceHasBeenAdded() {
        repo.setResourceRepo(new FakeResourceIsNotPresentRepo())
                .setUserRepo(new FakeCoordinatorFoundRepo());
        Request request = createAddResourceReq(1, PROJECTOR);

        IResponse response = request.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canReturnNotFoundIfResourceTypeHasNotFound() {
        repo.setResourceRepo(new FakeResourceTypeIsNotPresentRepo())
                .setUserRepo(new FakeCoordinatorFoundRepo());
        Request request = createAddResourceReq(1, "Tomato");

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnAlreadyHasIfResourceHasNotBeenAdded() {
        repo.setResourceRepo(new FakeResourceIsPresentRepo())
                .setUserRepo(new FakeCoordinatorFoundRepo());
        Request request = createAddResourceReq(1, PROJECTOR);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.ALREADY_HAS);
    }

    @Test
    public void canReturnNotValidForAddResourceReqIfRoleIsEmployee() {
        repo.setResourceRepo(new FakeResourceIsNotPresentRepo())
                .setUserRepo(new FakeUserFoundRepo("user@unn.ru"));
        Request request = new AddResourceReq(employee, 1, PROJECTOR);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_VALID);
    }

    @Test
    public void canReturnOkIfResourceHasBeenRemoved() {
        repo.setResourceRepo(new FakeResourceIsPresentRepo())
                .setUserRepo(new FakeCoordinatorFoundRepo());
        final int id = 1;
        Request addRequest = createAddResourceReq(id, PROJECTOR);
        addRequest.exec();
        Request removeRequest = new RemoveResourceReq(addRequest.getRole(), id);
        removeRequest.setRepository(repo);

        IResponse response = removeRequest.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canReturnNotFoundIfResourceHasNotBeenRemoved() {
        repo.setResourceRepo(new FakeResourceIsNotPresentRepo())
                .setUserRepo(new FakeCoordinatorFoundRepo());
        Request request = new RemoveResourceReq(defaultRole, 1);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnNotValidForRemoveResourceReqIfRoleIsEmployee() {
        repo.setResourceRepo(new FakeResourceIsPresentRepo())
                .setUserRepo(new FakeUserFoundRepo("user@unn.ru"));
        Request request = new RemoveResourceReq(employee, 1);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_VALID);
    }

    private Request createAddResourceReq(final int id, final String type) {
        Request request = new AddResourceReq(defaultRole, id, type);
        request.setRepository(repo);

        return request;
    }

}
