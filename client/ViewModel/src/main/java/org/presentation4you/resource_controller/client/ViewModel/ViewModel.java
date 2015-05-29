package org.presentation4you.resource_controller.client.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.presentation4you.resource_controller.client.RootDataManagement.DataManagement;
import org.presentation4you.resource_controller.client.RootDataManagement.IDataManagement;
import org.presentation4you.resource_controller.commons.Request.AddResourceReq;
import org.presentation4you.resource_controller.commons.Request.IRequest;
import org.presentation4you.resource_controller.commons.Request.RemoveResourceReq;
import org.presentation4you.resource_controller.commons.Role.Coordinator;

public class ViewModel {
    private final StringProperty txtResourceId = new SimpleStringProperty();
    private final StringProperty txtResourceType = new SimpleStringProperty();
    private final IDataManagement dm = new DataManagement();

    public ViewModel() {
        txtResourceId.set("");
        txtResourceType.set("");
    }

    public void addResource() {
        Integer id = Integer.parseInt(getTxtResourceId());
        String type = getTxtResourceType();
        IRequest request = new AddResourceReq(new Coordinator().setLogin("admin").setPassword("admin"),
                id, type);
        dm.send(request);
    }

    public void removeResource() {
        Integer id = Integer.parseInt(getTxtResourceId());
        IRequest request = new RemoveResourceReq(new Coordinator().setLogin("admin").setPassword("admin"), id);
        dm.send(request);
    }

    public final String getTxtResourceId() {
        return txtResourceId.get();
    }

    public final String getTxtResourceType() {
        return txtResourceType.get();
    }

    public StringProperty txtResourceIdProperty() {
        return txtResourceId;
    }

    public StringProperty txtResourceTypeProperty() {
        return txtResourceType;
    }
}
