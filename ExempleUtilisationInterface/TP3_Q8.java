package ExempleUtilisationInterface;
import java.util.Scanner;

import gestionError.DeplacementImpossibleException;
import interfaceGraphique.*;
import tp_cartes.Carte;
import tp_cartes.Paquet;
import tp_cartes.PaquetColonne;
import tp_cartes.PaquetDistributeur;

public class TP3_Q8 {
	public static void main(String[] args) throws DeplacementImpossibleException {
		Scanner scan = new Scanner(System.in);
		InterfaceKlondike itp = new InterfaceKlondike();
		PaquetDistributeur p= new PaquetDistributeur();
		p.melangePaquet();
		//initialisation carte
		Carte c1= new Carte("dos.png");
		//initialisation paquet colonne
		PaquetColonne paquetOrdonne1 = new PaquetColonne(p, 1);
		Paquet paquetNonOrdonne1 = new Paquet(p, 0);
		PaquetColonne paquetOrdonne2 = new PaquetColonne(p, 1);
		Paquet paquetNonOrdonne2 = new Paquet(p, 1);
		PaquetColonne paquetOrdonne3 = new PaquetColonne(p, 1);
		Paquet paquetNonOrdonne3 = new Paquet(p, 2);
		PaquetColonne paquetOrdonne4 = new PaquetColonne(p, 1);
		Paquet paquetNonOrdonne4 = new Paquet(p, 3);
		PaquetColonne paquetOrdonne5 = new PaquetColonne(p, 1);
		Paquet paquetNonOrdonne5 = new Paquet(p, 4);
		PaquetColonne paquetOrdonne6 = new PaquetColonne(p, 1);
		Paquet paquetNonOrdonne6 = new Paquet(p, 5);
		PaquetColonne paquetOrdonne7 = new PaquetColonne(p, 1);
		Paquet paquetNonOrdonne7 = new Paquet(p, 6);

		//configuration initiale du jeu
		System.out.println("Ajout d'une carte de dos dans l'emplacement 0 => Pioche");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.definirCarte(c1, 0);
		
		//initialisation Tableau du plateau de jeu avec 2 tas par colonne
		
		//affichage colonne 1
		System.out.println("Ajout d'une carte dans l'emplacement 7 => C1");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ajouterUneCarte(paquetOrdonne1.getTop(), 7);
		//pas de carte non visible initialement dans l'emplacement 7
		
		//affichage colonne 2
		System.out.println("Ajout d'une carte de dos et une visible dans l'emplacement 8 => C2");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ajouterUneCarte(c1, 8);
		itp.ajouterUneCarte(paquetOrdonne2.getTop(), 8);
		
		//affichage colonne 3
		System.out.println("Ajout de deux cartes de dos et une visible dans l'emplacement 9 => C3");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ajouterUneCarte(c1, 9);
		itp.ajouterUneCarte(c1, 9);
		itp.ajouterUneCarte(paquetOrdonne3.getTop(), 9);
		
		//affichage colonne 4
		System.out.println("Ajout de trois cartes de dos et une visible dans l'emplacement 10 => C4");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ajouterUneCarte(c1, 10);
		itp.ajouterUneCarte(c1, 10);
		itp.ajouterUneCarte(c1, 10);
		itp.ajouterUneCarte(paquetOrdonne4.getTop(), 10);
		
		//affichage colonne 5
		System.out.println("Ajout de quatre cartes de dos et une visible dans l'emplacement 11 => C5");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ajouterUneCarte(c1, 11);
		itp.ajouterUneCarte(c1, 11);
		itp.ajouterUneCarte(c1, 11);
		itp.ajouterUneCarte(c1, 11);
		itp.ajouterUneCarte(paquetOrdonne5.getTop(), 11);
		
		//affichage colonne 6
		System.out.println("Ajout de cinq cartes de dos et une visible dans l'emplacement 12 => C6");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ajouterUneCarte(c1, 12);
		itp.ajouterUneCarte(c1, 12);
		itp.ajouterUneCarte(c1, 12);
		itp.ajouterUneCarte(c1, 12);
		itp.ajouterUneCarte(c1, 12);
		itp.ajouterUneCarte(paquetOrdonne6.getTop(), 12);
		
		//affichage colonne 7
		System.out.println("Ajout de six cartes de dos et une visible dans l'emplacement 13 => C7");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ajouterUneCarte(c1, 13);
		itp.ajouterUneCarte(c1, 13);
		itp.ajouterUneCarte(c1, 13);
		itp.ajouterUneCarte(c1, 13);
		itp.ajouterUneCarte(c1, 13);
		itp.ajouterUneCarte(c1, 13);
		itp.ajouterUneCarte(paquetOrdonne7.getTop(), 13);

		//fermeture fenêtre
		System.out.println("Fermeture de la fenêtre.");
		System.out.println("Tapez <entrez> pour continuer.");
		scan.nextLine();
		itp.ferme();
	}

}
