package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.RequestsFields.RequestsFields;

import java.util.Calendar;
import java.util.List;

public interface IRequestRepo {
    boolean canAddRequest(final int resourceId, final Calendar from,
                          final Calendar to, final String login) throws IllegalArgumentException;
    int addRequest(final int resourceId, final Calendar from,
                   final Calendar to, final String login);

    boolean hasRequest(final int id);
    void removeRequest(final int id);
    String getRequestOwner(final int id);
    void updateRequest(final int id, final boolean isApproved);
    List<RequestsFields> getRequests(RequestsFields match);
}
