package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.RequestsFields.RequestsFields;

import java.util.Calendar;
import java.util.List;

public class FakeDuplicatedRequestRepo implements IRequestRepo {
    @Override
    public boolean canAddRequest(int resourceId, Calendar from, Calendar to, String login) {
        return false;
    }

    @Override
    public int addRequest(int resourceId, Calendar from, Calendar to, String login) {
        return -1;
    }

    @Override
    public boolean hasRequest(final int id) {
        return false;
    }

    @Override
    public void removeRequest(final int id) {

    }

    @Override
    public String getRequestOwner(final int id) {
        return null;
    }

    @Override
    public void updateRequest(int id, boolean isApproved) {

    }

    @Override
    public List<RequestsFields> getRequests(RequestsFields match) {
        return null;
    }
}
