package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServeurIntf  extends Remote{

	public String messageBienvenue() throws RemoteException;
	
	public void ecrire(String string) throws Exception;
	
	public List<String> getMessages(int pos)throws Exception;
	
	public List<String> getMessages()throws Exception;
	
	public int getNbMsgSend() throws Exception;

}
