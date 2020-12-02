package serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CBClient  extends UnicastRemoteObject implements CBClientIntf{


	private static final long serialVersionUID = -7084597101394286125L;
	
	public CBClient() throws RemoteException{
		super();
	}

	@Override
	public void receiveNewMessage(String name, String msg) throws RemoteException {
		System.out.println("\n"+name +": "+ msg+"\n");
	}

}

