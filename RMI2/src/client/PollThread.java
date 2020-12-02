package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PollThread extends Thread{
	Client c;
	List<String> listeN;
	public PollThread(Client chatClient) {
		c =chatClient;
		listeN = new ArrayList<String>();
		
	}


	public void run() {
		while (true) { 
			if (c.listNouveaux.size()==0) { //  liste de msg du client est vide ?
				try {
					listeN = c.Serveur.getMessages(); // demande tous les messages au serveur 
				} catch (Exception e) {}

				System.out.println(listeN); // affiche ces messages 
				c.listNouveaux =listeN;    // liste de msg du client est egale a celle dans recuperé 
				// le dernier msg devient incicateur 
				c.pos = c.listNouveaux.indexOf(c.listNouveaux.get(c.listNouveaux.size()-1));
			}else { // la liste de message du client n'est pas vide 
				try {
					listeN = c.Serveur.getMessages(c.pos); // demande au serveur les msg a partir de l'indicateur 
					if (listeN.size()!=0) {

						for (String string : listeN) { //pour tout msg recuperé
							c.listNouveaux.add(string); // ajout du msg a la liste du client 
							System.out.println("Nouveau message: "+string); // affichage du msg 
						}
					}
					c.pos = c.listNouveaux.indexOf(c.listNouveaux.get(c.listNouveaux.size()-1)); // l'indicateur  prend la position du dernier message 
				} catch (Exception e) {}
			}

		}
	}
	

}
