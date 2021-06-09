package tp_cartes;


public class PaquetColonne extends Paquet {
	//constructeur avec n cartes du paquet distrib
	public PaquetColonne(PaquetDistributeur p, int n) {
		//2 objets/colonne => 1 tas cachees herité et 1 tas visible herité
		super(p, n); 
	}
	//ajoute une carte au sommet du paquet si couleur alterne et valeur precedente
	public void ajoutCarte(Carte c) {
		//si paquet vide j'ajoute carte que si Roi
		if (this.isEmpty() && c.getValeur().equals(Valeur.roi))
			super.ajoutCarte(c);
		//paquet non vide j'ajoute carte que si teinte...
		//...differente et carte en param est la precedente
		else if (!this.isEmpty() && this.getTop().precedeDiffTeinte(c))
			super.ajoutCarte(c);
		else 
			throw new IllegalArgumentException("Erreur -> Deplacement Impossible!");	
	}
	//ajoute une carte de paquet cache à paquet visible
	public void ajoutCarteVisible(Carte c) {
		//si paquet visible vide j'ajoute carte de paquet cache
		if (this.isEmpty())
			super.ajoutCarte(c);
		else 
			throw new IllegalArgumentException("Erreur -> Deplacement Impossible!");
	}
}
