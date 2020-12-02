package serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Serveur extends UnicastRemoteObject implements ServeurIntf {

	private static final long serialVersionUID = 1L;
	static List<String> listeDeMessages;
	private int nbMsgSend =0;

	protected Serveur() throws RemoteException {
		super();
		listeDeMessages = new ArrayList<String>();
		listeDeMessages.add("Bonjour");
		listeDeMessages.add("Merci ");
		listeDeMessages.add("Bonne navigaton");
	}

	
	@Override
	public String messageBienvenue() throws RemoteException {
		//String string = listeDeMessages.get((int) (Math.random()*listeDeMessages.size()));
		System.out.println("Nouveau client!"); // arrivée d'un nouveau client 
		return "Bienvenu";
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, InterruptedException {
		try {
			LocateRegistry.createRegistry(1099);	
		} catch (Exception e) {}
		
		Serveur chatServeur = new Serveur();
		Naming.rebind("//localhost/RmiServer", chatServeur);
		System.out.println("Serveur Prêt");

		System.out.println(listeDeMessages);
	}


	@Override
	public void ecrire(String string) throws Exception {
		listeDeMessages.add(string);
		nbMsgSend++;
		System.out.println(listeDeMessages);

	}


	@Override //retourne  les messages a partir d'une position 
	public List<String> getMessages(int pos ) throws Exception {
		List<String> listePos = new ArrayList<String>();
		
		for (int i = pos+1; i < listeDeMessages.size(); i++) {
			listePos.add(listeDeMessages.get(i));
		}
		return listePos;
	}


	@Override // retourne tous les messages 
	public List<String> getMessages() throws Exception {
		// TODO Auto-generated method stub
		return listeDeMessages;
	}


	@Override
	public int getNbMsgSend() throws Exception {
		// TODO Auto-generated method stub
		return nbMsgSend ;
	}

	

}
