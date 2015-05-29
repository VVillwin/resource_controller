package org.presentation4you.resource_controller.client.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.presentation4you.resource_controller.client.Authentication.Authentication;
import org.presentation4you.resource_controller.client.RootDataManagement.DataManagement;
import org.presentation4you.resource_controller.client.RootDataManagement.IDataManagement;
import org.presentation4you.resource_controller.commons.Request.AddResourceReq;
import org.presentation4you.resource_controller.commons.Request.IRequest;
import org.presentation4you.resource_controller.commons.Request.RemoveResourceReq;
import org.presentation4you.resource_controller.commons.Role.IRole;

public class ViewModel {
    private final StringProperty txtResourceId = new SimpleStringProperty();
    private final StringProperty txtResourceType = new SimpleStringProperty();
    //private final StringProperty txtLogin = new SimpleStringProperty();
    //private final StringProperty txtPassword = new SimpleStringProperty();

    private final IDataManagement dm = new DataManagement();
    private IRole role;

    public ViewModel() {
        role = Authentication.getInstance().getRole();
        txtResourceId.set("");
        txtResourceType.set("");
        //txtLogin.set("");
        //txtPassword.set("");
    }

    public void addResource() {
        Integer id = Integer.parseInt(getTxtResourceId());
        String type = getTxtResourceType();
        IRequest request = new AddResourceReq(role,
                id, type);
        dm.send(request);
    }

    public void removeResource() {
        Integer id = Integer.parseInt(getTxtResourceId());
        IRequest request = new RemoveResourceReq(role, id);
        dm.send(request);
    }

    public final String getTxtResourceId() {
        return txtResourceId.get();
    }

    public final String getTxtResourceType() {
        return txtResourceType.get();
    }

    /*public final String getTxtLogin() {
        return txtLogin.get();
    }

    public final String getTxtPassword() {
        return txtPassword.get();
    }*/

    public StringProperty txtResourceIdProperty() {
        return txtResourceId;
    }

    public StringProperty txtResourceTypeProperty() {
        return txtResourceType;
    }

    /*public StringProperty txtLoginProperty() {
        return txtLogin;
    }

    public StringProperty txtPasswordProperty() {
        return txtPassword;
    }*/
}
