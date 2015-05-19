package org.presentation4you.resource_controller.server.Receiver;

import org.presentation4you.resource_controller.commons.Request.IRequest;
import org.presentation4you.resource_controller.commons.Response.IResponse;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IReceiver extends Remote {
    IResponse post(IRequest request) throws RemoteException;
}
