package org.presentation4you.resource_controller.server.Repository;

import java.util.Calendar;

public class FakeResourceIsFreeRequestRepo implements IRequestRepo {
    private final int id = 1;

    @Override
    public boolean canAdd(int resourceId, Calendar from, Calendar to, String login) {
        return true;
    }

    @Override
    public int add(int resourceId, Calendar from, Calendar to, String login) {
        return id;
    }

    @Override
    public boolean has(final int id) {
        if (this.id == id) {
            return true;
        }
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
