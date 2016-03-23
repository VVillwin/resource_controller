package org.presentation4you.resource_controller.commons.Response;

import org.presentation4you.resource_controller.commons.RequestsFields.ResourcesFields;

import java.util.List;

public class GetResourcesResp extends Response {
    private static final long serialVersionUID = 7964127608142370557L;

    private List<ResourcesFields> resources;

    public static IResponse buildGetResourcesResp(List<ResourcesFields> resources) {
        if (resources == null) {
            return new GetResourcesResp();
        } else {
            return new GetResourcesResp(resources);
        }
    }

    public List<ResourcesFields> getResources() {
        return resources;
    }

    private GetResourcesResp() {
        setIsNotFound();
    }

    private GetResourcesResp(List<ResourcesFields> resources) {
        this.resources = resources;
        setIsOk();
    }
}
