package tp_cartes;

import gestionError.DeplacementImpossibleException;

public class PaquetColonne extends Paquet {
	//constructeur avec n cartes du paquet distrib
	public PaquetColonne(PaquetDistributeur p, int n) throws DeplacementImpossibleException {
		//2 objets/colonne => 1 tas cachees herité et 1 tas visible
		super(p, n); 
	}
	//ajoute une carte au sommet du paquet si couleur alterne et valeur precedente
	public void ajoutCarte(Carte c) throws DeplacementImpossibleException {
		//si paquet vide j'ajoute carte que si Roi
		if (this.isEmpty() && c.getValeur().equals(Valeur.roi))
			super.ajoutCarte(c);
		//paquet non vide j'ajoute carte que si teinte...
		//...differente et carte en param est la precedente
		else if (!this.isEmpty() && this.getTop().precedeDiffTeinte(c))
			super.ajoutCarte(c);
		else 
			throw new DeplacementImpossibleException();	
	}
	//ajoute une carte de paquet cache à paquet visible
	public void ajoutCarteVisible(Carte c) throws DeplacementImpossibleException {
		//si paquet visible vide j'ajoute carte de paquet cache
		if (this.isEmpty())
			super.ajoutCarte(c);
		else 
			throw new DeplacementImpossibleException();
	}
//	//retourne vrai si la derniere carte de col exp peut etre pose sur col recev
//	public boolean deplacePlusieurs(PaquetColonne exp, PaquetColonne recev, int nbCarte) {
//		//si paquet receveur vide renvoie vrai si la carte = Roi
//		if (recev.isEmpty() && exp.getLastCarte().getValeur().equals(Valeur.roi))
//			return true;
//		//si paquet receveur non vide retourne vrai si carte de exp = precede de recev et teinte alterne
//		else if (!recev.isEmpty() && recev.getTop().precedeDiffTeinte(exp.getCarteIndice(nbCarte)))
//			return true;
//		//si non retourne faux
//		return false;
//	}
//
//	//retire et ajoute n cartes d'une colonne vers une autre colonne
//	public void deplaceNcarteColCol(PaquetColonne exp, PaquetColonne recev, int nbCarte) {
//		for (int i=nbCarte; i<0; i--) {
//			if (deplacePlusieurs(exp, recev, nbCarte))
//				recev.ajoutCarte(exp.removeCarteInd(i));
//			else
//				throw new IllegalArgumentException();
//		}
//	}
//	//retourne vrai si la carte de col exp peut etre pose sur col recev
//	public boolean deplace(PaquetColonne exp, PaquetColonne recev) {
//		//si paquet receveur vide renvoie vrai si la carte = Roi
//		if (recev.isEmpty() && exp.getTop().getValeur().equals(Valeur.roi))
//			return true;
//		//si paquet receveur non vide retourne vrai si carte de exp = precede de recev et teinte alterne
//		else if (!recev.isEmpty() && recev.getTop().precedeDiffTeinte(exp.getTop()))
//			return true;
//		//si non retourne faux
//		return false;
//	}
//	//deplace carte du haut de paquet exp en haut du paquet recev 
//	public void deplaceColCol(PaquetColonne exp, PaquetColonne recev) {
//		if (deplace(exp, recev))
//			recev.ajoutCarte(exp.removeTop());
//		else
//			throw new IllegalArgumentException();
//	}
//	//retourne vrai si carte paquet colonne peut être ajouté dans paquet pieux
//	public boolean deplaceColPieu(PaquetColonne exp, PaquetPieux recev) {
//		//si paquet receveur vide renvoie vrai si la carte de exp = As
//		if (recev.isEmpty() && exp.getTop().getValeur().equals(Valeur.as))
//			return true;
//		//si paquet receveur non vide retourne vrai si carte de exp = suivante et meme couleur que recev
//		else if (!recev.isEmpty() && recev.getTop().precedeMemeCouleur(exp.getTop()))
//			return true;
//		//si non retourne faux
//		return false;
//	}
//	//methode transfert carte de colonne vers pieu
//	public void ColPieu(PaquetColonne exp, PaquetPieux recev) {
//		if(deplaceColPieu(exp, recev))
//			recev.ajoutCarte(exp.removeTop());
//		else
//			throw new IllegalArgumentException();
//	}

}
