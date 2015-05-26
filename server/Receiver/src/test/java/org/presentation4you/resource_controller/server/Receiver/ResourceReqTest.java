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
    public static final String PROJECTOR = "Projector";
    private IRole defaultRole = new Coordinator().setLogin("admin")
                                                 .setPassword("admin");
    private IntegrationTestsRepository itr = new IntegrationTestsRepository();
    private IRepositoryWrapper repo = new RepositoryWrapper()
                                .setResourceRepo(itr);
    private final int resId = 1;

    public int getResId() {
        return resId;
    }

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
        Request request = createAddResourceReq(resId, PROJECTOR);

        IResponse response = request.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canAddResource() {
        Request request = createAddResourceReq(resId, PROJECTOR);

        request.exec();

        assertTrue(itr.hasResource(resId));
    }

    @Test
    public void canReturnNotFoundIfResourceTypeHasNotFound() {
        Request request = createAddResourceReq(resId, "Tomato");

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnAlreadyHasIfResourceHasNotBeenAdded() {
        Request request = createAddResourceReq(resId, PROJECTOR);
        request.exec();

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.ALREADY_HAS);
    }

    @Test
    public void canReturnNotValidForAddResourceReqIfRoleIsEmployee() {
        Request request = new AddResourceReq(new Employee(), resId, PROJECTOR);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_VALID);
    }

    @Test
    public void canReturnOkIfResourceHasBeenRemoved() {
        Request addRequest = createAddResourceReq(resId, PROJECTOR);
        addRequest.exec();
        Request removeRequest = new RemoveResourceReq(addRequest.getRole(), resId);
        removeRequest.setRepository(repo);

        IResponse response = removeRequest.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canRemoveResource() {
        Request addRequest = createAddResourceReq(resId, PROJECTOR);
        addRequest.exec();
        Request removeRequest = new RemoveResourceReq(addRequest.getRole(), resId);
        removeRequest.setRepository(repo);

        removeRequest.exec();

        assertFalse(itr.hasResource(resId));
    }

    @Test
    public void canReturnNotFoundIfResourceHasNotBeenRemoved() {
        Request request = new RemoveResourceReq(new Coordinator(), resId);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnNotValidForRemoveResourceReqIfRoleIsEmployee() {
        Request request = new RemoveResourceReq(new Employee(), resId);
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
