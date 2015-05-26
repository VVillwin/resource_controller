package org.presentation4you.resource_controller.commons.RequestsFields;

import java.sql.Timestamp;
import java.util.Calendar;

public class RequestsFields {
    private int id = 0;
    private String login;
    private Calendar from;
    private Calendar to;
    private int resourceId = 0;
    private String resourceType;
    private boolean lookForIsApproved = false;
    private boolean isApproved = false;

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
        this.from = (Calendar) from.clone();
        return this;
    }

    public RequestsFields setFrom(Timestamp from) {
        this.from = Calendar.getInstance();
        this.from.setTimeInMillis(from.getTime());
        return this;
    }

    public Calendar getTo() {
        return to;
    }

    public RequestsFields setTo(Calendar to) {
        this.to = (Calendar) to.clone();
        return this;
    }

    public RequestsFields setTo(Timestamp to) {
        this.to = Calendar.getInstance();
        this.to.setTimeInMillis(to.getTime());
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

    public boolean getIsApproved() {
        return isApproved;
    }

    public RequestsFields setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
        setLookForIsApproved(true);
        return this;
    }

    public int getId() {
        return id;
    }

    public RequestsFields setId(int id) {
        this.id = id;
        return this;
    }

    public boolean lookForIsApproved() {
        return lookForIsApproved;
    }

    private void setLookForIsApproved(boolean lookForIsApproved) {
        this.lookForIsApproved = lookForIsApproved;
    }
}
