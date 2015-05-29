package org.presentation4you.resource_controller.client.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.presentation4you.resource_controller.client.ViewModel.ViewModel;

public class ResourceController {
    @FXML
    private ViewModel viewModel;
    @FXML
    private Button btnRemoveResource;
    @FXML
    private Button btnAddResource;
    @FXML
    private TextField txtResourceId;
    @FXML
    private TextField txtResourceType;

    @FXML
    public void initialize() {
        txtResourceId.textProperty().bindBidirectional(viewModel.txtResourceIdProperty());
        txtResourceType.textProperty().bindBidirectional(viewModel.txtResourceTypeProperty());

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
    }
}
