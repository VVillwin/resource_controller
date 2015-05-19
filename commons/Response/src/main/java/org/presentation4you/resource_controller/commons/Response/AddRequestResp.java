package org.presentation4you.resource_controller.commons.Response;

public class AddRequestResp extends Response {
    private int id;

    public AddRequestResp(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
