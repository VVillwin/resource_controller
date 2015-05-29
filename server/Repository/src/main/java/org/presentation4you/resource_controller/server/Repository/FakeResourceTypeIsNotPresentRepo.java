package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.RequestsFields.ResourcesFields;

import java.util.List;
import java.util.NoSuchElementException;

public class FakeResourceTypeIsNotPresentRepo implements IResourceRepo {
    @Override
    public boolean hasResource(int id) {
        return false;
    }

    @Override
    public void addResource(int id, int typeId) {
    }

    @Override
    public void removeResource(int id) {
    }

    @Override
    public int getResourceType(String type) throws NoSuchElementException {
        throw new NoSuchElementException();
    }

    @Override
    public List<ResourcesFields> getResources(ResourcesFields match) {
        return null;
    }
}
