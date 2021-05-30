package tp_cartes;

import gestionError.*;
import interfaceGraphique.InterfaceKlondike;

public class Pioche extends Paquet {
	private Paquet defausse; //paquet visible
	//constructeur
	public Pioche(PaquetDistributeur p, InterfaceKlondike itp) throws DeplacementImpossibleException {
		//initialise la partie a piocher avec toutes les cartes restantes du distrib
		//et creer un paquet vide pour la defausse
		super(p, p.getNombreCartePaquet());
		defausse = new Paquet();
		itp.ajouterUneCarte(getTop(), 0);
	}
	public Paquet getDefausse() {
		return defausse;
	}
	//si pioche contient carte deplace carte du haut de la pioche à la defausse
	public void deplaceCartePiocheDefausse(Pioche p, InterfaceKlondike itp) throws PiocheVideException, DeplacementImpossibleException {
		if (!p.isEmpty()) {
			p.getTop().rendVisible();
			defausse.ajoutCarte(p.removeTop());
			itp.ajouterUneCarte(defausse.getTop(), 1);
			if(p.isEmpty())
				itp.vider(0);
		}
		else {
			throw new PiocheVideException();
		}
	}
	//recycle pioche en deplacant carte du haut de la defausse à la pioche
	public void recyclePioche(Pioche p, InterfaceKlondike itp) throws RecyclageErrorException, DeplacementImpossibleException{
		int nbCarteDefausse=defausse.getNombreCartePaquet();
		if (p.isEmpty()) {
			for (int i=0; i<nbCarteDefausse; i++) {
				p.ajoutCarte(defausse.removeTop());
				p.getTop().rendCache();
				itp.ajouterUneCarte(p.getTop(), 0);
				itp.vider(1);	
			}
		}
		else
			throw new RecyclageErrorException();
	}
	//retourne vrai si la carte de defausse exp peut etre pose sur pieux recev
	public boolean deplaceDefPieu(PaquetPieux recev) throws DeplacementImpossibleException {
		//si paquet receveur vide renvoie vrai si la carte = as
		if (recev.isEmpty() && this.defausse.getTop().getValeur().equals(Valeur.as))
			return true;
		//si paquet receveur non vide retourne vrai si carte de exp = suivante de recev et même couleur
		else if (!recev.isEmpty() && recev.getTop().precedeMemeCouleur(this.defausse.getTop()))
			return true;
		//si non retourne faux
		return false;
	}
	//deplace carte de defausse à pieux
	public void deplaceDefaussePieu(PaquetPieux recev, InterfaceKlondike itp, int emp) throws DeplacementImpossibleException {
		if (deplaceDefPieu(recev)) {
			recev.ajoutCarte(this.defausse.removeTop());
			itp.ajouterUneCarte(recev.getTop(), emp);
			if(!defausse.isEmpty())
				itp.definirCarte(defausse.getTop(), 1);
			else
				itp.vider(1);
		}
		else
			throw new DeplacementImpossibleException();
	}
	//retourne vrai si la carte de defausse exp peut etre pose sur col recev
	public boolean deplaceDefCol(PaquetColonne recev) throws DeplacementImpossibleException {
		//si paquet receveur vide renvoie vrai si la carte = Roi
		if (recev.isEmpty() && this.defausse.getTop().getValeur().equals(Valeur.roi))
			return true;
		//si paquet receveur non vide retourne vrai si carte de exp = precede de recev et teinte alterne
		else if (!recev.isEmpty() && recev.getTop().precedeDiffTeinte(this.defausse.getTop()))
			return true;
		//si non retourne faux
		return false;
	}
	//deplace carte de defausse à colonne
	public void deplaceDefausseCol(PaquetColonne recev, InterfaceKlondike itp, int emp) throws DeplacementImpossibleException{
		if (deplaceDefCol(recev)) {
			recev.ajoutCarte(this.defausse.removeTop());
			itp.ajouterUneCarte(recev.getTop(), emp);
			if(!defausse.isEmpty())
				itp.definirCarte(defausse.getTop(), 1);
			else
				itp.vider(1);
		}
		else
			throw new DeplacementImpossibleException();
	}
}
