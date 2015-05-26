package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.RequestsFields.RequestsFields;

import java.util.Calendar;
import java.util.List;

public class FakeResourceIsFreeRequestRepo implements IRequestRepo {
    private final int id = 1;

    @Override
    public boolean canAddRequest(int resourceId, Calendar from, Calendar to, String login) {
        return true;
    }

    @Override
    public int addRequest(int resourceId, Calendar from, Calendar to, String login) {
        return id;
    }

    @Override
    public boolean hasRequest(final int id) {
        if (this.id == id) {
            return true;
        }
        return false;
    }

    @Override
    public void removeRequest(final int id) {

    }

    @Override
    public String getRequestOwner(final int id) {
        return "user";
    }

    @Override
    public void updateRequest(int id, boolean isApproved) {

    }

    @Override
    public List<RequestsFields> getRequests(RequestsFields match) {
        return null;
    }
}
