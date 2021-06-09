package tp_cartes;

import java.util.InputMismatchException;
import java.util.Scanner;

import interfaceGraphique.*;

public class PlateauJeu {
	private Scanner scan;
	private MenuKlondike menu;
	private InterfaceKlondike itp;
	private PaquetDistributeur p;
	private TableauKlondike tableau;
	private Fondation fondation; 
	private Pioche pioche;
	
	public PlateauJeu() {
		scan = new Scanner(System.in);
		menu= new MenuKlondike();
		itp = new InterfaceKlondike();
		p = new PaquetDistributeur();
		p.melangePaquet();
		tableau= new TableauKlondike(p, itp);
		fondation = new Fondation(); 
		pioche = new Pioche(p, itp);
	}
	//retourne vrai si carte paquet colonne peut être ajouté dans paquet pieux
	public boolean deplaceColPieu(int indiceExp, PaquetPieux recev) {
		//si paquet receveur vide renvoie vrai si la carte de exp = As
		if (recev.isEmpty() && tableau.getPaquetColonneVisible(indiceExp).getTop().getValeur().equals(Valeur.as))
			return true;
		//si paquet receveur non vide retourne vrai si carte de exp = suivante et meme couleur que recev
		else if (!recev.isEmpty() && recev.getTop().precedeMemeCouleur(tableau.getPaquetColonneVisible(indiceExp).getTop()))
			return true;
		//si non retourne faux
		return false;
	}
	//convertit indice paquet recev en emplacement interface graphique
	public int convertIndToEmp(int indiceRecev) {
		return (indiceRecev+7);
	}
	//methode transfert automatique fin de partie
	public void ColPieuNfois() {
		//parcours tableau de paquets visibles et distribue les bonnes cartes 
		//dans pieux jusqu'à vider les paquets colonnes 
		int somme= sommeCartePaquetVisible();
		while (somme!=0) {
			for (int i=0; i<7; i++) {
				for (int j=0; j<4; j++) {
					if (!tableau.getPaquetColonneVisible(i).isEmpty()) {
						if (deplaceColPieu(i, fondation.getPaquetPieux(j))) {
							fondation.getPaquetPieux(j).ajoutCarte(tableau.getPaquetColonneVisible(i).removeTop());
							somme= somme + tableau.getPaquetColonneVisible(i).getNombreCartePaquet();
							itp.ajouterUneCarte(fondation.getPaquetPieux(j).getTop(), (j+3));
							//si paquet colonne vide, vide interface graphique
							if (tableau.getPaquetColonneVisible(i).isEmpty()) 
								itp.vider(convertIndToEmp(i));
							//si paquet visible contient carte retire une carte de l'interface graphique
							else if (!tableau.getPaquetColonneVisible(i).isEmpty()) 
								itp.retirerDesCartes(1, convertIndToEmp(i));
						}
					}
				}
			}
		}
	}
	//calcul somme carte paquet visible
	public int sommeCartePaquetVisible() {
		int somme=0;
		for (int i=0; i<7; i++) {
			somme= somme + tableau.getPaquetColonneVisible(i).getNombreCartePaquet();
		}
		return somme;
	}
	//méthode retourne vrai si on est dans une configuration de fin de partie
	//avec pioche vide, defausse vide, paquet cache vide
	public boolean ConfigFinale() {
		if (pioche.isEmpty() && pioche.DefausseEmpty() && paquetCacheEmpty()) {
			return true;
		}
		else
			return false;
	}
	//retourne vrai si tous les paquets colonnes cachees sont vides
	public boolean paquetCacheEmpty() {
		boolean cache= true;
		for (int i=0; i<7 && cache; i++) {
			if (!tableau.getPaquetColonneCache(i).isEmpty()) {
				cache=false;
			}
		}
		return cache;
	}
	
	//retourne choix pieux ssi string compris entre a et d
	public String lireChoixPieux() {	
		Scanner scan= new Scanner(System.in);
		String nomPieux;
		nomPieux = scan.next();
		while (!saisiPieux(nomPieux)) {
			System.out.println("Incorrect. Entrez un emplacement correct entre A et D.");
			System.out.println("Votre saisi : ");
			nomPieux = scan.next();
		}
		return nomPieux;
	}
	//retourne vrai si la saisi correspond à un pieux
	public boolean saisiPieux(String nomPieux) {
		if (nomPieux.equalsIgnoreCase("A"))
			return true;
		else if (nomPieux.equalsIgnoreCase("B"))
			return true;
		else if (nomPieux.equalsIgnoreCase("C"))
			return true;
		else if (nomPieux.equalsIgnoreCase("D"))
			return true;
		else 
			return false;
	}
	
	//retourne choix colonne ssi entier compris entre 1 et 7
	public int lireChoixColonne() {
		Scanner scan= new Scanner(System.in);
		boolean saisi= false;
		int indiceColonne= -1;
		while (!saisi) {
			try {
				indiceColonne = scan.nextInt();
				scan.nextLine();
			}		
			catch (InputMismatchException e) {
				System.out.println("Incorrect. Entrez un nombre compris entre 1 et 7.");
				System.out.println("Votre saisi : ");
				scan.nextLine();
				continue;
			}
			saisi=true;
		}
		while (indiceColonne<1 || indiceColonne>7) {
			System.out.println("Incorrect. Entrez un nombre compris entre 1 et 7.");
			System.out.println("Votre saisi : ");
			indiceColonne = scan.nextInt();
		}
		return indiceColonne;
	}
	
	//retourne nbcartes à deplacer ssi entier compris entre 2 et n cartes paquet expediteur
	public int lireNbCartes(int indiceExp) {
		Scanner scan= new Scanner(System.in);
		boolean saisi= false;
		int nbcarte= -1;
		while (!saisi) {
			try {
				nbcarte = scan.nextInt();
				scan.nextLine();
			}		
			catch (InputMismatchException e) {
				System.out.println("Erreur -> déplacez 2 à n cartes visibles de colonne expeditrice!");
				System.out.println("Votre saisi : ");
				scan.nextLine();
				continue;
			}
			saisi=true;
		}
		return nbcarte;
	}
	
	
	public void Jouer() {
		//Menu choix
		menu.afficherMenu();
		menu.lireChoixUtilisateur();
		while (menu.choixUtilisateur()) {
			try {
				//choix 1 piocher
				if (menu.getChoix()== 1) { 
					if (!pioche.isEmpty()) 
						pioche.deplaceCartePiocheDefausse(pioche, itp);
					else	
						System.out.println("Erreur -> Pioche vide!");
				}
				//choix 2 defausse->colonne
				else if (menu.getChoix()==2) {
					//si defausse vide affiche une erreur
					if (!pioche.DefausseEmpty()) {
						int indiceRecev;
						System.out.println("Entrez numero colonne receveuse : ");
						indiceRecev= lireChoixColonne()-1;
						if (pioche.deplaceDefCol(tableau.getPaquetColonneVisible(indiceRecev)))
							pioche.deplaceDefausseCol(tableau.getPaquetColonneVisible(indiceRecev), itp, tableau.convertIndToEmp(indiceRecev));
						else
							System.out.println("Erreur -> Deplacement Impossible!");
					}
					else
						System.out.println("Erreur -> Defausse vide!");
				}
				//choix 3 defausse->pieux
				else if (menu.getChoix()==3) {
					if (!pioche.DefausseEmpty()) {
						String nomPieux;
						System.out.println("Entrez nom pieux receveur : ");
						nomPieux = lireChoixPieux();
						if (pioche.deplaceDefPieu(fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux))))
							//convertit nom en indice et cherche paquet pieux à l'indice
							pioche.deplaceDefaussePieu(fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux)), 
									itp, fondation.convertNomToEmp(nomPieux));
						else
							System.out.println("Erreur -> Deplacement Impossible!");
					}
					else
						System.out.println("Erreur -> Defausse vide!");
				}
				//choix 4 pieux->colonne
				else if (menu.getChoix()==4) {
					String nomPieux;
					int indiceRecev;
					System.out.println("Entrez nom pieux expediteur : ");
					nomPieux = lireChoixPieux();
					if (!fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux)).isEmpty()) {
						System.out.println("Entrez numero colonne receveuse : ");
						indiceRecev= lireChoixColonne()-1;
						if (fondation.deplacePCol(fondation.convertNomToInd(nomPieux), tableau.getPaquetColonneVisible(indiceRecev)))
							fondation.deplacePieuxCol(fondation.convertNomToInd(nomPieux), tableau.getPaquetColonneVisible(indiceRecev), 
									itp, tableau.convertIndToEmp(indiceRecev), fondation.convertNomToEmp(nomPieux));
						else
							System.out.println("Erreur -> Deplacement Impossible!");
					}
					else
						System.out.println("Erreur -> Pieux vide!");

				}
				//choix 5 colonne->pieux
				else if (menu.getChoix()==5) {
					int indiceExp;
					String nomPieux;
					System.out.println("Entrez numero colonne expeditrice : ");
					indiceExp= lireChoixColonne()-1;
					System.out.println("Entrez nom pieux receveur : ");
					nomPieux = lireChoixPieux();
					if (deplaceColPieu(indiceExp, fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux))))
						tableau.ColPieu(indiceExp, fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux)), itp, fondation.convertNomToEmp(nomPieux));
					else
						System.out.println("Erreur -> Deplacement Impossible!");
				}
				//choix 6 colonne->colonne
				else if (menu.getChoix()==6) {
					int indiceExp, indiceRecev;
					System.out.println("Entrez numero colonne expeditrice : ");
					indiceExp= lireChoixColonne()-1;
					if (!tableau.getPaquetColonneVisible(indiceExp).isEmpty()) {
						System.out.println("Entrez numero colonne receveuse : ");
						indiceRecev= lireChoixColonne()-1;
						if (tableau.deplace(tableau.getPaquetColonneVisible(indiceExp), tableau.getPaquetColonneVisible(indiceRecev)))
							tableau.deplaceColCol(indiceExp, indiceRecev, itp);
						else
							System.out.println("Erreur -> Deplacement Impossible!");
					}
					else
						System.out.println("Erreur -> Paquet expediteur vide!");
				}
				//choix 7 colonne(n)->colonne
				else if (menu.getChoix()==7) {
					int indiceExp, indiceRecev, nbCarte;
					System.out.println("Entrez numero colonne expeditrice : ");
					indiceExp= lireChoixColonne()-1;
					if (!tableau.getPaquetColonneVisible(indiceExp).isEmpty()) {
						System.out.println("Entrez numero colonne receveuse : ");
						indiceRecev= lireChoixColonne()-1;
						System.out.println("Entrez le nombre de cartes a deplacer : ");
						//faire moins 1 pour tomber sur le bon indice de la nième carte
						nbCarte= lireNbCartes(indiceExp)-1;
						if (nbCarte>=1 && nbCarte<tableau.getPaquetColonneVisible(indiceExp).getNombreCartePaquet())
							tableau.deplaceNcarteColCol(indiceExp, indiceRecev, nbCarte, itp);
						else
							System.out.println("Erreur -> déplacez 2 à n cartes visibles de colonne expeditrice!");
					}
					else
						System.out.println("Erreur -> Paquet expediteur vide!");
				}
				//choix 8 recycler pioche
				else if (menu.getChoix()==8) {
					if (pioche.isEmpty())
						pioche.recyclePioche(pioche, itp);
					else
						System.out.println("Erreur -> Recyclage impossible! Pioche encore pleine.");
				}

				//ranger les cartes restantes automatiquement si configuration finale
				else if (menu.getChoix()==9) {
					if (ConfigFinale()) {
						System.out.println("Bravo! Vous avez terminez la partie.");
						ColPieuNfois();
						break;
					}
					else
						System.out.println("Vous n'êtes pas en configuration de fin (= pioche vide, defausse vide, zéro cartes cachees)!");
				}
				//choix 10 Fin
				else if (menu.getChoix()==10) {
					//fermeture fenêtre
					System.out.println("Fermeture de la fenêtre. A bientôt!");
					itp.ferme();
					break;
				}

			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			//re initialise choix utilisateur	
			System.out.println();
			menu.afficherMenu();
			menu.lireChoixUtilisateur();
		}
	}
}

