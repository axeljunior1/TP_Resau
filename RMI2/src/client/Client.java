package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import serveur.ServeurIntf;

public class Client {
	ServeurIntf Serveur ;
	List<String> listNouveaux = new ArrayList<String>();
	int pos =0;
	String last ;
	
	
	
	public Client() throws MalformedURLException, RemoteException, NotBoundException {
		Serveur =  (ServeurIntf) Naming.lookup("//localhost/RmiServer");
		
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Client chatClient =new Client();
		System.out.println(chatClient.Serveur.messageBienvenue());

	
		new Threadchat(chatClient).start(); // Thread ecriture 
		
		new PollThread(chatClient).start(); //Thread lecture 
	}

}

