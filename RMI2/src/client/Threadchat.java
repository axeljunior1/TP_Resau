package client;

import java.util.Scanner;

public class Threadchat extends Thread {
	Client ch;
	public Threadchat(Client chatClient) throws Exception {
		ch =chatClient;
	}
	
	
	
	public void run(){
			String string =  new Scanner(System.in).nextLine(); //entrée au clavier 

			while (!string.equals("") ) { // l'entrée est =! ""(vide, non nul)
				try {
					ch.Serveur.ecrire(string); //on envoi un message au serveur 
				} catch (Exception e) {			e.printStackTrace();			}
				
				string = new Scanner(System.in).nextLine(); // demande une autre entrée
			}
		
	}
}
