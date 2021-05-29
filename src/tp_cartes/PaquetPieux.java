package tp_cartes;

import java.util.ArrayList;

public class PaquetPieux extends Paquet {
	//constructeur
	public PaquetPieux() {
		super();
	}
	//ajoute une carte au sommet du paquet si
	//meme couleur et valeur suivante
	public void ajoutCarte(Carte c) {
		//si paquet vide j'ajoute carte que si c'est un As
		if (this.isEmpty() && c.getValeur().equals(Valeur.as))
			super.ajoutCarte(c);
		//paquet non vide j'ajoute carte que si meme couleur... 
		//...et carte en param est la suivante
		else if (!this.isEmpty() && this.getTop().precedeMemeCouleur(c))
			super.ajoutCarte(c);
		else 
			throw new IllegalArgumentException();
	}
	//retourne vrai si la carte de pieux exp peut etre pose sur paquet col recev
	public boolean deplace(PaquetPieux exp, PaquetColonne recev) {
		//si paquet receveur vide renvoie vrai si la carte = as
		if (recev.isEmpty() && exp.getTop().getValeur().equals(Valeur.as))
			return true;
		//si paquet receveur non vide retourne vrai si carte de exp = suivante de recev et même couleur
		else if (!recev.isEmpty() && recev.getTop().precedeMemeCouleur(exp.getTop()))
			return true;
		//si non retourne faux
		return false;
	}
	public void deplacePieuCol(PaquetPieux exp, PaquetColonne recev) {
		if(deplace(exp, recev))
			recev.ajoutCarte(exp.removeTop());
		else
			throw new IllegalArgumentException();
	}
}
