package org.presentation4you.resource_controller.commons.Response;

public class AddRequestResp extends Response {
    private static final long serialVersionUID = 994519155571802832L;

    private int id;

    public AddRequestResp(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
