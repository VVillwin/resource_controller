package org.presentation4you.resource_controller.server.Repository;

import java.util.NoSuchElementException;

public interface IResourceRepo {
    boolean hasResource(final int id);
    void addResource(final int id, final int typeId);
    void removeResource(final int id);
    int getResourceType(final String type) throws NoSuchElementException;
}
