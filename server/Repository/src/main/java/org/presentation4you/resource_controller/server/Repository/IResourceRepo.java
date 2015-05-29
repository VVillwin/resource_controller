package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.RequestsFields.ResourcesFields;

import java.util.List;
import java.util.NoSuchElementException;

public interface IResourceRepo {
    boolean hasResource(final int id);
    void addResource(final int id, final int typeId);
    void removeResource(final int id);
    int getResourceType(final String type) throws NoSuchElementException;
    List<ResourcesFields> getResources(final ResourcesFields match);
}
