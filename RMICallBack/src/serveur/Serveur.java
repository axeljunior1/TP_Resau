package serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Serveur extends UnicastRemoteObject implements ServeurIntf {

	
	private static final long serialVersionUID = 1L;
	public List<String> messages; 
	public HashMap<String, CBClientIntf> CBClientmap;

	protected Serveur() throws RemoteException {

		messages   = new ArrayList<>();
		CBClientmap = new HashMap<>();
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException, InterruptedException {
		try {
			LocateRegistry.createRegistry(1099);	
		} catch (Exception e) {}
		
		Serveur chatServeur = new Serveur();
		Naming.rebind("//localhost/RmiServer", chatServeur);
		System.out.println("Serveur Prêt");
		
		for (Map.Entry entree : chatServeur.CBClientmap.entrySet()) {
			System.out.println(entree.getValue());
		}
	}

	@Override
	public void addCBClient(String name, CBClientIntf CBClient) throws RemoteException {
		CBClientmap.put(name, CBClient);
	}

	@Override
	public void notifyMe(String name) throws RemoteException {
		String msg =name+" a rejoint la discuss";
		for (Map.Entry entree : CBClientmap.entrySet()) {
			if(!entree.getKey().equals(name)) {
			((CBClientIntf) entree.getValue()).receiveNewMessage(name, "à rejoint la discuss");
			}else {
				((CBClientIntf) entree.getValue()).receiveNewMessage(name, "Vous avez rejoint la discuss");
			}
			
		}
	}

	@Override
	public boolean isAUser(String name) throws RemoteException {
		if(CBClientmap.containsKey(name))return false;
		else return true;
	}

	@Override
	public void sendMessage(String name, String msg) throws RemoteException {
		messages.add(msg);
		for (Map.Entry entree : CBClientmap.entrySet()) {
			if(!entree.getKey().equals(name)) {
			((CBClientIntf) entree.getValue()).receiveNewMessage(name, msg);
			}
		}
	}

	@Override
	public void close(String name) throws RemoteException {
		for (Map.Entry entree : CBClientmap.entrySet()) {
			if(!entree.getKey().equals(name)) {
			((CBClientIntf) entree.getValue()).receiveNewMessage(name, "a quité la discuss");
			}
		}
		CBClientmap.remove(name);
	}

	@Override
	public List<String> AnciensMessages() throws RemoteException {
		List<String> msg = new ArrayList<>();
		for(String message : messages) {
			msg.add(message);
		}
		return msg;
	}



}
