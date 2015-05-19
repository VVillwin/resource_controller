package org.presentation4you.resource_controller.commons.Response;

public enum ResponseStatus {
    OK("OK"),
    NOT_VALID("The request is not valid"),
    REPOWRAPPER_ERROR("The request has not been processed due to internal error"),
    NOT_FOUND("The request has not been found"),
    ALREADY_HAS("The repository has already contained the item"),
    HAS_CONFLICT("The repository has already contained an approved request " +
            "conflicting with this request");

    private final String name;

    ResponseStatus(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}