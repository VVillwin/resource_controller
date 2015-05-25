package org.presentation4you.resource_controller.server.Receiver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.presentation4you.resource_controller.commons.Request.AddResourceReq;
import org.presentation4you.resource_controller.commons.Request.RemoveResourceReq;
import org.presentation4you.resource_controller.commons.Request.Request;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.Coordinator;
import org.presentation4you.resource_controller.commons.Role.Employee;
import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResourceReqTest {
    private static final String PROJECTOR = "Projector";
    private IRole defaultRole = new Coordinator().setLogin("admin")
                                                 .setPassword("admin");
    private IntegrationTestsRepository itr = new IntegrationTestsRepository();
    private IRepositoryWrapper repo = new RepositoryWrapper()
                                .setResourceRepo(itr);

    @Before
    public void prepareTestDB() {
        itr.deleteAll();
    }

    @After
    public void deleteAll() {
        itr.deleteAll();
    }

    @Test
    public void canReturnOkIfResourceHasBeenAdded() {
        Request request = createAddResourceReq(1, PROJECTOR);

        IResponse response = request.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canAddResource() {
        int resId = 1;
        Request request = createAddResourceReq(resId, PROJECTOR);

        request.exec();

        assertTrue(itr.has(resId));
    }

    @Test
    public void canReturnNotFoundIfResourceTypeHasNotFound() {
        Request request = createAddResourceReq(1, "Tomato");

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnAlreadyHasIfResourceHasNotBeenAdded() {
        Request request = createAddResourceReq(1, PROJECTOR);
        request.exec();

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.ALREADY_HAS);
    }

    @Test
    public void canReturnNotValidForAddResourceReqIfRoleIsEmployee() {
        Request request = new AddResourceReq(new Employee(), 1, PROJECTOR);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_VALID);
    }

    @Test
    public void canReturnOkIfResourceHasBeenRemoved() {
        final int id = 1;
        Request addRequest = createAddResourceReq(id, PROJECTOR);
        addRequest.exec();
        Request removeRequest = new RemoveResourceReq(addRequest.getRole(), id);
        removeRequest.setRepository(repo);

        IResponse response = removeRequest.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canRemoveResource() {
        final int id = 1;
        Request addRequest = createAddResourceReq(id, PROJECTOR);
        addRequest.exec();
        Request removeRequest = new RemoveResourceReq(addRequest.getRole(), id);
        removeRequest.setRepository(repo);

        removeRequest.exec();

        assertFalse(itr.has(id));
    }

    @Test
    public void canReturnNotFoundIfResourceHasNotBeenRemoved() {
        Request request = new RemoveResourceReq(new Coordinator(), 1);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnNotValidForRemoveResourceReqIfRoleIsEmployee() {
        Request request = new RemoveResourceReq(new Employee(), 1);
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
