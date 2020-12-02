package serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class Client {
	static Scanner sc = new Scanner(System.in);
	ServeurIntf Serveur; 
	private String name;
	
	public  Client(String name) throws RemoteException, MalformedURLException, NotBoundException {
		Serveur = (ServeurIntf)Naming.lookup("//localhost/RmiServer");
		this.name = name;
	}
	
	public String getName() {		return name;	}

	public void setName(String name) {		this.name = name;	}



	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		 
		  System.out.print("Entrer votre nom : ");
		@SuppressWarnings({ "resource" })
		String name = sc.nextLine();
		CBClient cbclient = new CBClient();
		
	
		
		Client client = new Client(name);
		
		
		while(!client.Serveur.isAUser(name)) {
    		System.out.print(" Nom d'utilisateur deja attribué: entre un autre \n");
        	name = sc.nextLine();
        }
		client.Serveur.addCBClient(name, cbclient);
		client.setName(name);
		System.out.println();
		if(!client.Serveur.AnciensMessages().isEmpty()) {
			System.out.println("\n Vous avez de nouveaux messages \n");
			System.out.println(client.Serveur.AnciensMessages());
		}
		
		client.Serveur.notifyMe(client.getName());
		
		System.out.print("Ecrire: ");
		String messsage = sc.nextLine();
		while (messsage!="q") {
			client.Serveur.sendMessage(name, messsage);
			System.out.print("Ecrire: ");
			messsage = sc.nextLine();
		}
		client.Serveur.close(name);
		sc.close();
		System.out.println("vous avez quité la discuss");
	}

}
