package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServeurIntf  extends Remote{	
	
	public void addCBClient(String name,CBClientIntf CBClient) throws RemoteException;
	
	public void notifyMe(String name) throws RemoteException;
	
	public boolean isAUser(String name)throws RemoteException;
	
	public void sendMessage(String name,String msg) throws RemoteException;
	
	public void close(String name) throws RemoteException;
	
	public  List<String> AnciensMessages()throws RemoteException;
}
