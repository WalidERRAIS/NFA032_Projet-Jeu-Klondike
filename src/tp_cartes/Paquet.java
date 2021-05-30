package tp_cartes;

import java.util.*;

import gestionError.DeplacementImpossibleException;

public class Paquet {
	//attribut
	protected Valeur [] valeur= Valeur.values();
	protected Couleur [] couleur= Couleur.values();
	protected ArrayList<Carte> paquet;
	//constructeur paquet vide
	public Paquet() {
		this.paquet= new ArrayList<Carte>();
	}
	//constructeur paquet avec n cartes du paquet distrib
	public Paquet(PaquetDistributeur p, int n) throws DeplacementImpossibleException {
		this.paquet= new ArrayList<Carte>();
		for (int i=0; i<n; i++) {
			this.paquet.add(0, p.removeTop());
		}
	}
	//ajoute une carte au sommet du paquet
	public void ajoutCarte(Carte c) throws DeplacementImpossibleException {
		this.paquet.add(0, c);
	}
	//retire la carte au sommet du paquet
	public Carte removeTop() throws DeplacementImpossibleException{
		if (!this.paquet.isEmpty())  
			return this.paquet.remove(0);
		else
			throw new DeplacementImpossibleException();
	}
	//retourne la carte au sommet du paquet
	public Carte getTop() throws DeplacementImpossibleException {
		if (!this.paquet.isEmpty())
			return this.paquet.get(0);
		else
			throw new DeplacementImpossibleException();
	}
	//retourne la carte à l'indice i du paquet
	public Carte getCarteIndice(int i) throws DeplacementImpossibleException{
		if (!this.paquet.isEmpty())
			return this.paquet.get(i);
		else
			throw new DeplacementImpossibleException();
	}
	//retourne le nombre de carte
	public int getNombreCartePaquet() {
		return this.paquet.size();
	}
	//retourne vrai si paquet vide
	public boolean isEmpty() {	
		return this.paquet.isEmpty();
	}
	public Carte getLastCarte() {
		return this.paquet.get(getNombreCartePaquet()-1);
	}
	public Carte removeLastCarte() throws DeplacementImpossibleException{
		if (!this.paquet.isEmpty())  
			return this.paquet.remove(getNombreCartePaquet()-1);
		else
			throw new DeplacementImpossibleException();
	}
	//retire carte a indice i
	public Carte removeCarteInd(int i) throws DeplacementImpossibleException{
		if (!this.paquet.isEmpty())  
			return this.paquet.remove(i);
		else
			throw new DeplacementImpossibleException();
	}
	//retourne vrai si la carte de paquet exp peut etre pose sur paquet recev
	//retourne vrai pour paquet sans ordre
	public boolean deplace(Paquet exp, Paquet recev) {
		return true;
	}
	public String toString() {
		String res="";
		for (int i=0; i<this.paquet.size(); i++) {
			res= res+this.paquet.get(i).toString()+"\n";
		}
		return res;
	}

}
