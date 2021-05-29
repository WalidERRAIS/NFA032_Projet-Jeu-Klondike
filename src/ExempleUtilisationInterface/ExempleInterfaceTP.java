package ExempleUtilisationInterface;
import java.util.Scanner;

import interfaceGraphique.*;
import tp_cartes.*;
public class ExempleInterfaceTP {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		InterfaceKlondike itp = new InterfaceKlondike();
		PaquetDistributeur p= new PaquetDistributeur();
		Paquet jeu = new Paquet(p, 52);
		Carte c3 = new Carte("dos.png");
		//test affichage interface
		System.out.println("Ajout d'une carte dans le tas A et la colonne 2");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.definirCarte(jeu.removeTop(), 3);
		itp.ajouterUneCarte(c3, 8);
		
		System.out.println("Ajout d'un tableau de carte dans 2 et d'une carte non visible dans B.");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		Carte[] tab = {jeu.removeTop(), c3, jeu.removeTop()};
		itp.ajouterLesCartes(tab, 8);
		itp.definirCarte(c3, 4);		
		System.out.println("Définition du contenu de la colonne 1.");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.definirLesCartes(tab, 7);
		System.out.println("Vider le tas A et retirer 3 cartes de 2.");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.vider(3);
		itp.retirerDesCartes(3, 8);
		System.out.println("Fermeture de la fenêtre.");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ferme();
	}
	

}
