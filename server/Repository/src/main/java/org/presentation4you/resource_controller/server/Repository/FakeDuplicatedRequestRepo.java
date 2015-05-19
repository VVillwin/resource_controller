package org.presentation4you.resource_controller.server.Repository;

import java.util.Calendar;

public class FakeDuplicatedRequestRepo implements IRequestRepo {
    @Override
    public boolean canAdd(int resourceId, Calendar from, Calendar to, String login) {
        return false;
    }

    @Override
    public int add(int resourceId, Calendar from, Calendar to, String login) {
        return -1;
    }

    @Override
    public boolean has(final int id) {
        return false;
    }

    @Override
    public void remove(final int id) {

    }

    @Override
    public String getRequestOwner(final int id) {
        return null;
    }

    @Override
    public void update(int id, boolean isApproved) {

    }
}
