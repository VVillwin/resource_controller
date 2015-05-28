package org.presentation4you.resource_controller.server.Receiver;

import org.presentation4you.resource_controller.commons.Request.IRequest;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.server.Repository.IRepositoryWrapper;
import org.presentation4you.resource_controller.server.Repository.IntegrationTestsRepository;
import org.presentation4you.resource_controller.server.Repository.RepositoryWrapper;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Receiver implements IReceiver {
    private IntegrationTestsRepository itr = new IntegrationTestsRepository();
    private IRepositoryWrapper repo = new RepositoryWrapper()
            .setResourceRepo(itr).setUserRepo(itr).setRequestRepo(itr);

    public Receiver() {
    }

    public IResponse post(IRequest request) {
        request.setRepository(repo);
        return request.exec();
    }

    public static void main(String args[]) {
        try {
            Receiver obj = new Receiver();
            IReceiver stub = (IReceiver) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(12345);
            registry.bind("IReceiver", stub);

            System.err.println("Receiver is ready");
        } catch (Exception e) {
            System.err.println("Receiver exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
