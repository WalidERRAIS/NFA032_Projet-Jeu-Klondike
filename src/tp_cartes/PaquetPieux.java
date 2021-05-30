package tp_cartes;

import java.util.ArrayList;

import gestionError.DeplacementImpossibleException;

public class PaquetPieux extends Paquet {
	//constructeur
	public PaquetPieux() {
		super();
	}
	//ajoute une carte au sommet du paquet si
	//meme couleur et valeur suivante
	public void ajoutCarte(Carte c) throws DeplacementImpossibleException {
		//si paquet vide j'ajoute carte que si c'est un As
		if (this.isEmpty() && c.getValeur().equals(Valeur.as))
			super.ajoutCarte(c);
		//paquet non vide j'ajoute carte que si meme couleur... 
		//...et carte en param est la suivante
		else if (!this.isEmpty() && this.getTop().precedeMemeCouleur(c))
			super.ajoutCarte(c);
		else 
			throw new DeplacementImpossibleException();
	}
//	//retourne vrai si la carte de pieux exp peut etre pose sur paquet col recev
//	public boolean deplace(PaquetPieux exp, PaquetColonne recev) throws DeplacementImpossibleException {
//		//si paquet receveur vide renvoie vrai si la carte = as
//		if (recev.isEmpty() && exp.getTop().getValeur().equals(Valeur.as))
//			return true;
//		//si paquet receveur non vide retourne vrai si carte de exp = suivante de recev et même couleur
//		else if (!recev.isEmpty() && recev.getTop().precedeMemeCouleur(exp.getTop()))
//			return true;
//		//si non retourne faux
//		return false;
//	}
//	public void deplacePieuCol(PaquetPieux exp, PaquetColonne recev) throws DeplacementImpossibleException {
//		if(deplace(exp, recev))
//			recev.ajoutCarte(exp.removeTop());
//		else
//			throw new IllegalArgumentException();
//	}
}
