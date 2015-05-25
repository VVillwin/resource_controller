package org.presentation4you.resource_controller.server.Repository;

import java.util.NoSuchElementException;

public interface IResourceRepo {
    boolean has(final int id);
    void add(final int id, final int typeId);
    void remove(final int id);
    int getType(final String type) throws NoSuchElementException;
}
