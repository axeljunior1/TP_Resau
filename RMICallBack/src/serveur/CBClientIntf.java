package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CBClientIntf extends Remote{
	
	public void receiveNewMessage(String name,String msg) throws RemoteException;

}
