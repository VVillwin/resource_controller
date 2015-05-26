package org.presentation4you.resource_controller.commons.RequestsFields;

import java.util.Calendar;

public class RequestsFields {
    private int id;
    private String login;
    private Calendar from;
    private Calendar to;
    private int resourceId;
    private String resourceType;
    private boolean isApproved;

    public String getLogin() {
        return login;
    }

    public RequestsFields setLogin(String login) {
        this.login = login;
        return this;
    }

    public Calendar getFrom() {
        return from;
    }

    public RequestsFields setFrom(Calendar from) {
        this.from = from;
        return this;
    }

    public Calendar getTo() {
        return to;
    }

    public RequestsFields setTo(Calendar to) {
        this.to = to;
        return this;
    }

    public int getResourceId() {
        return resourceId;
    }

    public RequestsFields setResourceId(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getResourceType() {
        return resourceType;
    }

    public RequestsFields setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public RequestsFields setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
        return this;
    }

    public int getId() {
        return id;
    }

    public RequestsFields setId(int id) {
        this.id = id;
        return this;
    }
}
