package tp_cartes;

import interfaceGraphique.*;

public class TableauKlondike {
	// 1 tableau de paquets de cartes cachees et 1 tableau de paquets de cartes visibles
	private PaquetColonne[] tableauCache= new PaquetColonne[7];
	private PaquetColonne[] tableauVisible= new PaquetColonne[7];
	//configuration initiale du jeu 
	public TableauKlondike(PaquetDistributeur p, InterfaceKlondike itp) {
		int j=7;
		for (int i=0; i<7; i++) {
			//initialise tableau paquets de cartes cachees 
			//et affichage de la carte selon visibilite à l'emplacement interface graphique
			tableauCache[i]= new PaquetColonne(p, i);
			//pas d'affichage à l'emplacement 7 pour paquet cachee indice 0 car paquet vide
			if (!tableauCache[i].isEmpty()) {
				//ajoute autant de carte à l'affichage que de cartes dans paquet cache
				for (int k=0; k<tableauCache[i].getNombreCartePaquet(); k++) 
					itp.ajouterUneCarte(tableauCache[i].getCarteIndice(k), j);
				
			}
			//initialise tableau paquets cartes cachees
			//et affichage de la carte selon visibilite à l'emplacement interface graphique
			tableauVisible[i]= new PaquetColonne(p, 1);
			tableauVisible[i].getTop().rendVisible(); //rend visible cartes dans paquet visible
			//ajoute autant de carte à l'affichage que de cartes dans paquet
			//pour paquet visible configuration initiale nb carte afficher = 1
			itp.ajouterUneCarte(tableauVisible[i].getTop(), j);
			j++; //incremente j => emplacement interface graphique
		}
	}
	
	public PaquetColonne getPaquetColonneCache(int indicePaquetColonne) {
		return (tableauCache[indicePaquetColonne]);
	}
	public PaquetColonne getPaquetColonneVisible(int indicePaquetColonne) {
		return (tableauVisible[indicePaquetColonne]);
	}
	//convertit indice paquet recev en emplacement interface graphique
	public int convertIndToEmp(int indiceRecev) {
		return (indiceRecev+7);
	}
	//retourne vrai si la carte de col exp peut etre pose sur col recev
	public boolean deplace(PaquetColonne exp, PaquetColonne recev) {
		//si paquet receveur vide renvoie vrai si la carte = Roi
		if (recev.isEmpty() && exp.getTop().getValeur().equals(Valeur.roi))
			return true;
		//si paquet receveur non vide retourne vrai si carte de exp = precede de recev et teinte alterne
		else if (!recev.isEmpty() && recev.getTop().precedeDiffTeinte(exp.getTop()))
			return true;
		//si non retourne faux
		return false;
	}

	//deplace carte du haut de paquet exp en haut du paquet recev 
	public void deplaceColCol(int indiceExp, int indiceRecev, InterfaceKlondike itp) {
		tableauVisible[indiceRecev].ajoutCarte(tableauVisible[indiceExp].removeTop());
		itp.ajouterUneCarte(tableauVisible[indiceRecev].getTop(), (convertIndToEmp(indiceRecev)));
		//deux paquets colonnes vides
		if (tableauVisible[indiceExp].isEmpty() && tableauCache[indiceExp].isEmpty())
			itp.vider(convertIndToEmp(indiceExp));
		//paquet visible vide et paquet cache contient carte
		else if (tableauVisible[indiceExp].isEmpty() && !tableauCache[indiceExp].isEmpty()) {
			tableauVisible[indiceExp].ajoutCarteVisible((tableauCache[indiceExp].removeTop()));
			tableauVisible[indiceExp].getTop().rendVisible();
			itp.retirerDesCartes(2, convertIndToEmp(indiceExp));
			itp.ajouterUneCarte(tableauVisible[indiceExp].getTop(), convertIndToEmp(indiceExp));
		}
		else if (!tableauVisible[indiceExp].isEmpty()) 
			itp.retirerDesCartes(1, convertIndToEmp(indiceExp));
	}
	//retourne vrai si carte paquet colonne peut être ajouté dans paquet pieux
	public boolean deplaceColPieu(int indiceExp, PaquetPieux recev) {
		//si paquet receveur vide renvoie vrai si la carte de exp = As
		if (recev.isEmpty() && tableauVisible[indiceExp].getTop().getValeur().equals(Valeur.as))
			return true;
		//si paquet receveur non vide retourne vrai si carte de exp = suivante et meme couleur que recev
		else if (!recev.isEmpty() && recev.getTop().precedeMemeCouleur(tableauVisible[indiceExp].getTop()))
			return true;
		//si non retourne faux
		return false;
	}
	//methode transfert carte de colonne vers pieu
	public void ColPieu(int indiceExp, PaquetPieux recev, InterfaceKlondike itp, int idEmpPieux) {
		recev.ajoutCarte(tableauVisible[indiceExp].removeTop());
		itp.ajouterUneCarte(recev.getTop(), idEmpPieux);
		//deux paquets colonnes vides
		if (tableauVisible[indiceExp].isEmpty() && tableauCache[indiceExp].isEmpty()) 
			itp.vider(convertIndToEmp(indiceExp));
		//paquet visible vide et paquet cache contient carte
		else if (tableauVisible[indiceExp].isEmpty() && !tableauCache[indiceExp].isEmpty()) {
			tableauVisible[indiceExp].ajoutCarteVisible((tableauCache[indiceExp].removeTop()));
			tableauVisible[indiceExp].getTop().rendVisible();
			itp.retirerDesCartes(2, convertIndToEmp(indiceExp));
			itp.ajouterUneCarte(tableauVisible[indiceExp].getTop(), convertIndToEmp(indiceExp));
		}
		//si paquet visible contient carte
		else if (!tableauVisible[indiceExp].isEmpty()) 
			itp.retirerDesCartes(1, convertIndToEmp(indiceExp));
	}
	
	//retourne vrai si la derniere carte de col exp peut etre pose sur col recev
	public boolean deplacePlusieurs(PaquetColonne exp, PaquetColonne recev, int indiceCarte) {
		//si paquet receveur vide renvoie vrai si la carte = Roi
		if (recev.isEmpty() && exp.getCarteIndice(indiceCarte).getValeur().equals(Valeur.roi))
			return true;
		//si paquet receveur non vide retourne vrai si carte de exp = precede de recev et teinte alterne
		else if (!recev.isEmpty() && recev.getTop().precedeDiffTeinte(exp.getCarteIndice(indiceCarte)))
			return true;
		//si non retourne faux
		return false;
	}

	//retire et ajoute n cartes d'une colonne vers une autre colonne
	public void deplaceNcarteColCol(int indiceExp, int indiceRecev, int nbCarte, InterfaceKlondike itp) {
		//nbcarte +1 parce que -1 a été fait dans plateau jeu
		Carte [] tab= new Carte[nbCarte+1];
		int j=0;
		for (int i=nbCarte; i>=0; i--) {
			if (deplacePlusieurs(tableauVisible[indiceExp], tableauVisible[indiceRecev], i)) {
				//initialise tableau de carte
				tab[j]= tableauVisible[indiceExp].getCarteIndice(i);
				tableauVisible[indiceRecev].ajoutCarte(tableauVisible[indiceExp].removeCarteInd(i));
				j++;
			}
			else {
				throw new IllegalArgumentException("Erreur -> Deplacement Impossible!");
			}
		}
		itp.ajouterLesCartes(tab, convertIndToEmp(indiceRecev));
		//deux paquets colonnes vides
		if (tableauVisible[indiceExp].isEmpty() && tableauCache[indiceExp].isEmpty())
			itp.vider(convertIndToEmp(indiceExp));
		//paquet visible vide et paquet cache contient carte
		else if (tableauVisible[indiceExp].isEmpty() && !tableauCache[indiceExp].isEmpty()) {
			tableauVisible[indiceExp].ajoutCarteVisible((tableauCache[indiceExp].removeTop()));
			tableauVisible[indiceExp].getTop().rendVisible();
			itp.retirerDesCartes(nbCarte+2, convertIndToEmp(indiceExp));
			itp.ajouterUneCarte(tableauVisible[indiceExp].getTop(), convertIndToEmp(indiceExp));
		}
		else if (!tableauVisible[indiceExp].isEmpty()) {
			itp.retirerDesCartes(nbCarte+1, convertIndToEmp(indiceExp));
		}
	}
	
}
