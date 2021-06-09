package tp_cartes;

import java.util.Random;

public class PaquetDistributeur extends Paquet {
	//constructeur
	public PaquetDistributeur() {
		for (int i=0; i<valeur.length; i++) {
			for (int j=0; j<couleur.length; j++) {
				this.paquet.add(new Carte(valeur[i], couleur[j], 
						(String.valueOf(valeur[i])+"_de_"+String.valueOf(couleur[j])+".png")));
			}
		}
	}
	//mélange paquet = tirage aléatoire n fois
	public void melangePaquet() {
		int nbAleatoire=tirageAleatoire();
		//nbTirage nombre de fois que vous voulez mélanger
		int nbTirage=1;
		while (nbTirage!=200) {
			//ajout à la fin de la carte tiré aléatoirement
			this.paquet.add(this.paquet.get(nbAleatoire));
			this.paquet.remove(nbAleatoire);
			nbAleatoire=tirageAleatoire();
			nbTirage++;
		}
	}
	//tirage aléatoire
	private static int tirageAleatoire() {
		Random random= new Random();
		int nbAleatoire = random.nextInt(52);
		return nbAleatoire;
	}
	//retire la carte au sommet du paquet distributeur 
	//removeTop !non red�fini! reutilise celle de la super class
	
	
	
	
	
	

}
