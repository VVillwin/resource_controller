package org.presentation4you.resource_controller.server.Repository;

public class FakeResourceIsNotPresentRepo implements IResourceRepo {
    public boolean has(int id) {
        return false;
    }

    public void add(int id, String type) {
    }

    public void remove(int id) {
    }
}
