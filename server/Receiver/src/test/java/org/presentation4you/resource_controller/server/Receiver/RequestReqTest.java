package org.presentation4you.resource_controller.server.Receiver;

import org.junit.Before;
import org.junit.Test;
import org.presentation4you.resource_controller.commons.Request.*;
import org.presentation4you.resource_controller.commons.Response.AddRequestResp;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
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

public class RequestReqTest {
    private static final String PROJECTOR = "Projector";
    private static final int ID = 1;
    final private String DATE_TO_TEST = "14/06/2015 14:30";
    private IRole defaultRole = new Employee().setLogin("user")
                                              .setPassword("user");
    private IntegrationTestsRepository itr = new IntegrationTestsRepository();
    private IRepositoryWrapper repo = new RepositoryWrapper().setRequestRepo(itr);


    @Before
    public void prepareTestDB() {
        itr.deleteAll();
    }

    /*@After
    public void deleteAll() {
        itr.deleteAll();
    }*/

    @Test
    public void canReturnOkIfRequestHasBeenAdded() {
        IResponse response = getIResponseAfterAddedRequestReq(ID, DATE_TO_TEST);

        assertTrue(response.isOk());
    }

    @Test
    public void canAddRequest() {
        AddRequestResp response = (AddRequestResp) getIResponseAfterAddedRequestReq(ID, DATE_TO_TEST);

        assertTrue(itr.hasRequest(response.getId()));
    }

    @Test
    public void canReturnAlreadyHasIfRequestHasNotBeenAdded() {
        getIResponseAfterAddedRequestReq(ID, DATE_TO_TEST);

        IResponse response = getIResponseAfterAddedRequestReq(ID, DATE_TO_TEST);

        assertEquals(response.getStatus(), ResponseStatus.ALREADY_HAS);
    }

    @Test
    public void canReturnOkIfRequestHasBeenRemoved() {
        AddRequestResp addReqResponse = (AddRequestResp) getIResponseAfterAddedRequestReq(ID, DATE_TO_TEST);
        IRequest request = new RemoveRequestReq(defaultRole, addReqResponse.getId());
        request.setRepository(repo);

        IResponse response = request.exec();

        assertEquals(ResponseStatus.OK, response.getStatus());
    }

    @Test
    public void canReturnNotValidForRemoveRequestIfRoleIsNotStoredOwner() {
        AddRequestResp addReqResponse = (AddRequestResp) getIResponseAfterAddedRequestReq(ID, DATE_TO_TEST);
        IRequest request = new RemoveRequestReq(defaultRole.setLogin("noSuchUser"), addReqResponse.getId());
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
        Request request = new AddRequestReq(defaultRole, resourceId, from, to);
        request.setRepository(repo);

        return request;
    }
}
