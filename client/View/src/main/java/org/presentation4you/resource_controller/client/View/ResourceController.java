package org.presentation4you.resource_controller.client.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.presentation4you.resource_controller.client.ViewModel.ViewModel;
import org.presentation4you.resource_controller.commons.RequestsFields.ResourcesFields;

public class ResourceController {
    @FXML
    private ViewModel viewModel;
    @FXML
    private Button btnRemoveResource;
    @FXML
    private Button btnAddResource;
    @FXML
    private Button btnGetResources;
    @FXML
    private TextField txtResourceId;
    @FXML
    private TextField txtResourceType;
    @FXML
    private TableView<ResourcesFields> tblResources;

    @FXML
    public void initialize() {
        txtResourceId.textProperty().bindBidirectional(viewModel.txtResourceIdProperty());
        txtResourceType.textProperty().bindBidirectional(viewModel.txtResourceTypeProperty());
        tblResources.itemsProperty().bindBidirectional(viewModel.tblGetResourcesProperty());

        btnAddResource.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addResource();
            }
        });

        btnRemoveResource.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.removeResource();
            }
        });

        btnGetResources.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getResources();
            }
        });
    }
}
