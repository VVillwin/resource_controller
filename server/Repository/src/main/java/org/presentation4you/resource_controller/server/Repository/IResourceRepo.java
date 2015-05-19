package org.presentation4you.resource_controller.server.Repository;

public interface IResourceRepo {
    boolean has(final int id);
    void add(final int id, final String type);
    void remove(final int id);
}
