package org.presentation4you.resource_controller.client.ViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.presentation4you.resource_controller.client.Authentication.Authentication;
import org.presentation4you.resource_controller.client.RootDataManagement.DataManagement;
import org.presentation4you.resource_controller.client.RootDataManagement.IDataManagement;
import org.presentation4you.resource_controller.commons.Request.AddResourceReq;
import org.presentation4you.resource_controller.commons.Request.GetResourcesReq;
import org.presentation4you.resource_controller.commons.Request.IRequest;
import org.presentation4you.resource_controller.commons.Request.RemoveResourceReq;
import org.presentation4you.resource_controller.commons.RequestsFields.ResourcesFields;
import org.presentation4you.resource_controller.commons.Response.GetResourcesResp;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.IRole;

import java.util.List;

public class ViewModel {
    private final StringProperty txtResourceId = new SimpleStringProperty();
    private final StringProperty txtResourceType = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<ResourcesFields>> tblResources =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());

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

    public void getResources() {
        Integer id;
        String type = null;

        if (getTxtResourceId().equals("")) {
            id = 0;
        } else {
            id = Integer.parseInt(getTxtResourceId());
        }
        if (!getTxtResourceType().equals("")) {
            type = getTxtResourceType();
        }
        IRequest request = new GetResourcesReq(role, id, type);

        IResponse response = dm.send(request);
        System.out.println(response.getStatus());
        if (response instanceof GetResourcesResp) {
            if (response.getStatus() == ResponseStatus.OK) {
                List<ResourcesFields> resources = ((GetResourcesResp) response).getResources();
                for(ResourcesFields res : resources) {
                    System.out.println(res.getId() + " " + res.getType());
                }
                tblResources.set(FXCollections.observableList(resources));
            }
        }
    }

    public final String getTxtResourceId() {
        return txtResourceId.get();
    }

    public final String getTxtResourceType() {
        return txtResourceType.get();
    }

    public ObservableList<ResourcesFields> getTblResources(){
        return tblResources.get();
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

    public ObjectProperty<ObservableList<ResourcesFields>> tblGetResourcesProperty() {
        return tblResources;
    }

    /*public StringProperty txtLoginProperty() {
        return txtLogin;
    }

    public StringProperty txtPasswordProperty() {
        return txtPassword;
    }*/
}
