package org.presentation4you.resource_controller.client.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.presentation4you.resource_controller.client.ViewModel.ViewModelEmployee;

public class ResourceControllerEmployee {
    @FXML
    private ViewModelEmployee viewModel;
    @FXML
    private Button btnGetResources;
    @FXML
    private Button btnAddRequest;
    @FXML
    private Button btnRemoveRequest;
    @FXML
    private Button btnGetRequests;
    @FXML
    private Button btnGetOwnRequests;
    @FXML
    private TextField txtResourceId;
    @FXML
    private TextField txtResourceType;
    @FXML
    private TextField txtRequestId;
    @FXML
    private TextField txtResIdForReq;
    @FXML
    private TextField txtResTypeForReq;
    @FXML
    private TextField txtFrom;
    @FXML
    private TextField txtTo;

    @FXML
    public void initialize() {
        txtResourceId.textProperty().bindBidirectional(viewModel.txtResourceIdProperty());
        txtResourceType.textProperty().bindBidirectional(viewModel.txtResourceTypeProperty());
        txtRequestId.textProperty().bindBidirectional(viewModel.txtRequestIdProperty());
        txtResIdForReq.textProperty().bindBidirectional(viewModel.txtResIdForReqProperty());
        txtResTypeForReq.textProperty().bindBidirectional(viewModel.txtResTypeForReqProperty());
        txtFrom.textProperty().bindBidirectional(viewModel.txtFromProperty());
        txtTo.textProperty().bindBidirectional(viewModel.txtToProperty());

        btnGetResources.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getResources();
            }
        });

        btnAddRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addRequest();
            }
        });

        btnRemoveRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.removeRequest();
            }
        });

        btnGetRequests.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getRequests();
            }
        });

        btnGetOwnRequests.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getOwnRequests();
            }
        });
    }
}

