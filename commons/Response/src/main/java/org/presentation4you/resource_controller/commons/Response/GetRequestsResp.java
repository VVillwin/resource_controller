package org.presentation4you.resource_controller.commons.Response;

import org.presentation4you.resource_controller.commons.RequestsFields.RequestsFields;

import java.util.List;

public class GetRequestsResp extends Response {
    private static final long serialVersionUID = -4357136122470324445L;

    private List<RequestsFields> requests;

    public static IResponse buildGetRequestsResp(List<RequestsFields> requests) {
        if (requests == null) {
            return new GetRequestsResp();
        } else {
            return new GetRequestsResp(requests);
        }
    }

    public List<RequestsFields> getRequests() {
        return requests;
    }

    private GetRequestsResp() {
        setIsNotFound();
    }

    private GetRequestsResp(List<RequestsFields> requests) {
        this.requests = requests;
        setIsOk();
    }
}
