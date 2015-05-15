package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.Response.GetUserInfoResp;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.Response;

import java.util.HashMap;
import java.util.Map;

public class FakeRepository implements IRepository {
    // for getUserInfo
    private String login;
    private String email;

    // for addResource and RemoveResource
    private Map resources = new HashMap();

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public IResponse getUserInfo(final String login) {
        GetUserInfoResp response = new GetUserInfoResp();
        if (login == this.login) {
            response.setLogin(login);
            response.setEmail(email);
        } else {
            response.setIsNotFound();
        }
        return response;
    }

    public IResponse addResource(final int id, final String type) {
        IResponse response = new Response();
        if (resources.containsKey(id)) {
            response.setAlreadyHas();
            return response;
        }
        resources.put(id, type);
        return response;
    }

    public IResponse removeResource(int id) {
        IResponse response = new Response();
        if (resources.containsKey(id)) {
            resources.remove(id);
            return response;
        }

        response.setIsNotFound();
        return response;
    }

    public boolean doesContainResource(Integer id) {
        return resources.containsKey(id);
    }
}
