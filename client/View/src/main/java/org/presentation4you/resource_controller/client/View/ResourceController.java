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
    private Button btnGetResources;
    @FXML
    private Button btnApproveRequest;
    @FXML
    private Button btnGetRequests;
    @FXML
    private Button btnGetPendingRequests;
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

        btnApproveRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.approveRequest();
            }
        });

        btnGetRequests.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getRequests();
            }
        });

        btnGetPendingRequests.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getPendingRequests();
            }
        });
    }
}
