package org.presentation4you.resource_controller.client.RootDataManagement;

import org.presentation4you.resource_controller.commons.Request.IRequest;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.server.Receiver.IReceiver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DataManagement implements IDataManagement {
    @Override
    public IResponse send(IRequest request) {
        try {
            Registry registry = LocateRegistry.getRegistry(12345);
            IReceiver stub = (IReceiver) registry.lookup("IReceiver");
            IResponse response = stub.post(request);
            System.out.println("response: " + response.getStatus());
            return response;
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
