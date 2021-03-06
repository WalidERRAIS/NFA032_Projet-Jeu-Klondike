package tp_cartes;

import interfaceGraphique.*;

public class Fondation {
	private PaquetPieux[] fondation= new PaquetPieux[4];
	public Fondation() {
		for(int i=0; i<4; i++) {
			fondation[i]= new PaquetPieux();
		}
	}

	//convertit nom paquet pieux recev en emplacement interface graphique
	public int convertNomToEmp(String nomPieux) {
		if (nomPieux.equalsIgnoreCase("A"))
			return 3;
		else if (nomPieux.equalsIgnoreCase("B"))
			return 4;
		else if (nomPieux.equalsIgnoreCase("C"))
			return 5;
		else
			return 6;
	}
	//convertit nom paquet pieux recev en indice pour trouver paquet dans tableau fondation
	public int convertNomToInd(String nomPieux) {
		if (nomPieux.equalsIgnoreCase("A"))
			return 0;
		else if (nomPieux.equalsIgnoreCase("B"))
			return 1;
		else if (nomPieux.equalsIgnoreCase("C"))
			return 2;
		else
			return 3;
	}
	public PaquetPieux getPaquetPieux(int indicePaquetPieux) {
		return (fondation[indicePaquetPieux]);
	}
	//retourne vrai si la carte de paquet pieux exp peut etre pose sur col recev
	public boolean deplacePCol(int indiceExp, PaquetColonne recev){
		//si paquet receveur vide renvoie vrai si la carte = Roi
		if (recev.isEmpty() && this.fondation[indiceExp].getTop().getValeur().equals(Valeur.roi))
			return true;
		//si paquet receveur non vide retourne vrai si carte de exp = precede de recev et teinte alterne
		else if (!recev.isEmpty() && recev.getTop().precedeDiffTeinte(this.fondation[indiceExp].getTop()))
			return true;
		//si non retourne faux
		return false;
	}
	//deplace carte de pieux à colonne
	public void deplacePieuxCol(int indiceExp, PaquetColonne recev, InterfaceKlondike itp, int idEmpCol, int idEmpPieux) {
		recev.ajoutCarte(this.fondation[indiceExp].removeTop());
		itp.ajouterUneCarte(recev.getTop(), idEmpCol);
		if(!fondation[indiceExp].isEmpty())
			itp.definirCarte(fondation[indiceExp].getTop(), idEmpPieux);
		else
			itp.vider(idEmpPieux);
	}
}
