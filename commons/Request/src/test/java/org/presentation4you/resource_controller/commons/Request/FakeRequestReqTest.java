package org.presentation4you.resource_controller.commons.Request;

import org.junit.After;
import org.junit.Test;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.Coordinator;
import org.presentation4you.resource_controller.commons.Role.Employee;
import org.presentation4you.resource_controller.commons.Role.IRole;
import org.presentation4you.resource_controller.server.Repository.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FakeRequestReqTest {
    private static final int ID = 1;
    final private String DATE_TO_TEST = "14/06/2015 14:30";
    private IRepositoryWrapper repo;
    private IRole role = new Employee().setLogin("user");

    @After
    public void removeFakeRepository() {
        repo = null;
    }

    @Test
    public void canReturnOkIfRequestHasBeenAdded() {
        repo = new RepositoryWrapper().setRequestRepo(new FakeResourceIsFreeRequestRepo());
        IResponse response = getIResponseAfterAddedRequestReq(ID, DATE_TO_TEST);

        assertTrue(response.isOk());
    }

    @Test
    public void canReturnAlreadyHasIfRequestHasNotBeenAdded() {
        repo = new RepositoryWrapper().setRequestRepo(new FakeDuplicatedRequestRepo());
        IResponse response = getIResponseAfterAddedRequestReq(ID, DATE_TO_TEST);

        assertEquals(response.getStatus(), ResponseStatus.ALREADY_HAS);
    }

    @Test
    public void canReturnHasConflictIfRequestHasNotBeenAdded() {
        repo = new RepositoryWrapper().setRequestRepo(new FakeRequestHasConflictRepo());
        IResponse response = getIResponseAfterAddedRequestReq(ID, DATE_TO_TEST);

        assertEquals(response.getStatus(), ResponseStatus.HAS_CONFLICT);
    }

    @Test
    public void canReturnOkIfRequestHasBeenRemoved() {
        repo = new RepositoryWrapper().setRequestRepo(new FakeResourceIsFreeRequestRepo());
        IRequest request = new RemoveRequestReq(role, ID);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(ResponseStatus.OK, response.getStatus());
    }

    @Test
    public void canReturnNotValidForRemoveRequestIfRoleIsNotStoredOwner() {
        repo = new RepositoryWrapper().setRequestRepo(new FakeRequestHasConflictRepo());
        Request request = new RemoveResourceReq(new Employee().setLogin("NoSuchUser"), ID);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_VALID);
    }

    @Test
    public void canReturnOkIfRequestHasBeenUpdated() {
        repo = new RepositoryWrapper().setRequestRepo(new FakeResourceIsFreeRequestRepo());
        IRequest request = new UpdateRequestReq(new Coordinator(), ID, true);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertTrue(response.isOk());
    }

    @Test
    public void canReturnNotFoundIfRequestHasNotBeenUpdated() {
        repo = new RepositoryWrapper().setRequestRepo(new FakeResourceIsFreeRequestRepo());
        IRequest request = new UpdateRequestReq(new Coordinator(), ID + 1, true);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_FOUND);
    }

    @Test
    public void canReturnNotValidForUpdateRequestIfRoleIsEmployee() {
        repo = new RepositoryWrapper().setRequestRepo(new FakeRequestHasConflictRepo());
        Request request = new UpdateRequestReq(role, ID, true);
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(response.getStatus(), ResponseStatus.NOT_VALID);
    }

    private IResponse getIResponseAfterAddedRequestReq(final int resourceId, final String dateFrom) {
        Calendar from = buildCalendarFromString(dateFrom);
        Calendar to = (Calendar) from.clone();
        to.add(Calendar.HOUR_OF_DAY, 1);
        Request request = createAddRequestReq(resourceId, from, to);

        return request.exec();
    }

    private Calendar buildCalendarFromString(String requiredDate) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date;
        try {
            date = formatter.parse(requiredDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Request createAddRequestReq(final int resourceId, final Calendar from,
                                        final Calendar to) {
        Request request = new AddRequestReq(role, resourceId, from, to);
        request.setRepository(repo);

        return request;
    }
}
