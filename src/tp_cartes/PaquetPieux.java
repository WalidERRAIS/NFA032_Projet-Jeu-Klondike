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
}
