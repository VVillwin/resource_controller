package org.presentation4you.resource_controller.server.Receiver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.presentation4you.resource_controller.commons.Request.GetUserInfoReq;
import org.presentation4you.resource_controller.commons.Request.LoginUserReq;
import org.presentation4you.resource_controller.commons.Request.Request;
import org.presentation4you.resource_controller.commons.Response.GetUserInfoResp;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.LoginUserResp;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.Coordinator;
import org.presentation4you.resource_controller.commons.Role.Employee;
import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserInfoReqTest {
    private IRole defaultRole = new Coordinator().setLogin("admin")
                                                 .setPassword("admin");
    private IntegrationTestsRepository itr = new IntegrationTestsRepository();
    private IRepositoryWrapper repo = new RepositoryWrapper().setUserRepo(itr);
    private final String login = "admin";
    private final String password = "admin";
    private final String email = "admin@dummy.unn.ru";

    @Before
    public void prepareTestDB() {
        itr.deleteAll();
    }

    @After
    public void deleteAll() {
        itr.deleteAll();
    }
    @Test
    public void canGetUserInfo() {
        Request request = new GetUserInfoReq(defaultRole, login);
        request.setRepository(repo);

        GetUserInfoResp response = (GetUserInfoResp) request.exec();

        assertEquals(email, response.getEmail());
    }

    @Test
    public void canReturnNotFoundIfUserDoesNotExist() {
        Request request = new GetUserInfoReq(defaultRole, "noSuchLogin");
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnNotValidForGetUserInfoReqIfRoleIsEmployee() {
        Request request = new GetUserInfoReq(new Employee(), login);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_VALID);
    }

    @Test
    public void canReturnOkIfLoginIsSuccessful() {
        Request request = new LoginUserReq(login, password);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canReturnNotFoundIfLoginIsNotCorrect() {
        Request request = new LoginUserReq("noSuchUser", password);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnNotFoundIfPasswordIsNotCorrect() {
        Request request = new LoginUserReq(login, "incorrectPassword");
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnEmployeeIfLoginIsSuccessful() {
        Request request = new LoginUserReq("user", "user");
        request.setRepository(repo);

        LoginUserResp response = (LoginUserResp) request.exec();

        assertEquals(response.getRole().getClass(), Employee.class);
    }

    @Test
    public void canReturnCoordinatorIfLoginIsSuccessful() {
        Request request = new LoginUserReq(login, password);
        request.setRepository(repo);

        LoginUserResp response = (LoginUserResp) request.exec();

        assertEquals(response.getRole().getClass(), Coordinator.class);
    }
}
