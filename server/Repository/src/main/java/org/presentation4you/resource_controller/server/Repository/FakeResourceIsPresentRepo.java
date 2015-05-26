package org.presentation4you.resource_controller.server.Repository;

import java.util.NoSuchElementException;

public class FakeResourceIsPresentRepo implements IResourceRepo {
    @Override
    public boolean hasResource(int id) {
        return true;
    }

    @Override
    public void addResource(int id, int typeId) {

    }

    @Override
    public void removeResource(int id) {

    }

    @Override
    public int getResourceType(String type) throws NoSuchElementException {
        return 0;
    }
}
