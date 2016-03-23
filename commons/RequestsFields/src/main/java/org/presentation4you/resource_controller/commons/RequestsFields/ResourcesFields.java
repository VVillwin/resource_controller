package org.presentation4you.resource_controller.commons.RequestsFields;

import java.io.Serializable;

public class ResourcesFields implements Serializable {
    private static final long serialVersionUID = -8381223739579239018L;

    private int id = 0;
    private String type;


    public int getId() {
        return id;
    }

    public ResourcesFields setId(int id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public ResourcesFields setType(String type) {
        this.type = type;
        return this;
    }
}
