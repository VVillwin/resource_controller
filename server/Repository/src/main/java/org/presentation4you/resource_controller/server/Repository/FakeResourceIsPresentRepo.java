package org.presentation4you.resource_controller.server.Repository;

public class FakeResourceIsPresentRepo implements IResourceRepo {
    public boolean has(int id) {
        return true;
    }

    public void add(int id, String type) {

    }

    public void remove(int id) {

    }
}
