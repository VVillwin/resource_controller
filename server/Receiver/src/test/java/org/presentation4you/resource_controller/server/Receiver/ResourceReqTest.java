package org.presentation4you.resource_controller.server.Receiver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.presentation4you.resource_controller.commons.Request.AddResourceReq;
import org.presentation4you.resource_controller.commons.Request.Request;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Role.Coordinator;
import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.IRepositoryWrapper;
import org.presentation4you.resource_controller.server.Repository.IntegrationTestsRepository;
import org.presentation4you.resource_controller.server.Repository.RepositoryWrapper;

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

    private Request createAddResourceReq(final int id, final String type) {
        Request request = new AddResourceReq(defaultRole, id, type);
        request.setRepository(repo);

        return request;
    }
}
