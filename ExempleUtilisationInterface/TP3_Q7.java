package ExempleUtilisationInterface;
import java.util.Scanner;

import interfaceGraphique.*;
import tp_cartes.*;
public class TP3_Q7 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		InterfaceKlondike itp = new InterfaceKlondike();
		//initialisation carte
		Carte c1,c2,c3,c4,c5,c6;
		c1= new Carte("dos.png");
		c2= new Carte(Valeur.roi, Couleur.coeur, "roi_de_coeur.png");
		c3= new Carte(Valeur.as, Couleur.trefle, "as_de_trefle.png");
		c4= new Carte(Valeur.trois, Couleur.pique, "trois_de_pique.png");
		c5= new Carte(Valeur.quatre, Couleur.pique, "quatre_de_pique.png");
		c6= new Carte(Valeur.cinq, Couleur.pique, "cinq_de_pique.png");
		Carte[] tab= {c1, c1, c1};
		Carte[] tab2= {c6, c5, c4};
		//rangement carte
		System.out.println("Ajout d'une carte de dos dans l'emplacement 0 => Pioche");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.definirCarte(c1, 0);
		
		System.out.println("Ajout d'un roi de coeur dans l'emplacement 1 => Défausse");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.definirCarte(c2, 1);
		
		System.out.println("Ajout d'un as de trèfle dans l'emplacement 6 => D");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.definirCarte(c3, 6);
		
		System.out.println("Ajout de trois cartes de dos dans l'emplacement 9 => C3");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.definirLesCartes(tab, 9);
		
		System.out.println("Ajout de 5, 4 et 3 de pique dans l'emplacement 10 => C4");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.definirLesCartes(tab2, 10);
		
		System.out.println("Fermeture de la fenêtre.");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ferme();
	}

}
