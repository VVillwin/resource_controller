package org.presentation4you.resource_controller.server.Repository;

import java.util.Calendar;

public interface IRequestRepo {
    boolean canAdd(final int resourceId, final Calendar from,
                   final Calendar to, final String login) throws IllegalArgumentException;
    int add(final int resourceId, final Calendar from,
             final Calendar to, final String login);

    boolean has(final int id);
    void remove(final int id);
    String getRequestOwner(final int id);

    void update(final int id, final boolean isApproved);
}
