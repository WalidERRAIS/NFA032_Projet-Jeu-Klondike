package tp_cartes;

import java.util.InputMismatchException;
import java.util.Scanner;

import gestionError.*;
import interfaceGraphique.InterfaceKlondike;

public class PlateauJeu {
	private Scanner scan;
	private MenuKlondike menu;
	private InterfaceKlondike itp;
	private PaquetDistributeur p;
	private TableauKlondike tableau;
	private Fondation fondation; 
	private Pioche pioche;
	
	public PlateauJeu() throws DeplacementImpossibleException {
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
	public boolean deplaceColPieu(int indiceExp, PaquetPieux recev) throws DeplacementImpossibleException {
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
	public void ColPieuNfois() throws DeplacementImpossibleException {
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
	public void Jouer() throws DeplacementImpossibleException, SaisiIncorrectException {
		//Menu choix
		menu.afficherMenu();
		menu.lireChoixUtilisateur();
		while (menu.choixUtilisateur()) {
			//choix 1 piocher
			if (menu.getChoix()== 1) { 
				try {
					pioche.deplaceCartePiocheDefausse(pioche, itp);
				}
				catch (PiocheVideException e) {
					System.out.println("Erreur -> Pioche vide!");
				}
			}
			//choix 2 defausse->colonne
			else if (menu.getChoix()==2) {
				//si defausse vide affiche une erreur
				if (!pioche.DefausseEmpty()) {
					int indiceRecev;
					try {
						System.out.println("Entrez numero colonne receveuse : ");
						indiceRecev= scan.nextInt()-1;
						pioche.deplaceDefausseCol(tableau.getPaquetColonneVisible(indiceRecev), itp, tableau.convertIndToEmp(indiceRecev));
					} catch (DeplacementImpossibleException e) {
						System.out.println("Erreur -> Deplacement Impossible!");
					}
					catch (InputMismatchException e) {
						System.out.println("Erreur -> Saisi Incorrect!");
						scan.nextLine();
					}
					catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Erreur -> Entrez un emplacement correct!");
					}
					catch (IndexOutOfBoundsException e) {
						System.out.println("Erreur -> Entrez un emplacement correct!");
					}
				}
				else
					System.out.println("Erreur -> Defausse vide!");
			}
			//choix 3 defausse->pieux
			else if (menu.getChoix()==3) {
				if (!pioche.DefausseEmpty()) {
					String nomPieux;
					try {
						System.out.println("Entrez nom pieux receveur : ");
						nomPieux = scan.next();
						//convertit nom en indice et cherche paquet pieux à l'indice
						pioche.deplaceDefaussePieu(fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux)), 
								itp, fondation.convertNomToEmp(nomPieux));
					} catch (DeplacementImpossibleException e) {
						System.out.println("Erreur -> Deplacement Impossible!");
					}
					catch (InputMismatchException e) {
						System.out.println("Erreur -> Entrez un emplacement correct!");
						scan.nextLine();
					}
					catch (SaisiIncorrectException e) {
						System.out.println("Erreur -> Saisi Incorrect!");
					}
				}
				else
					System.out.println("Erreur -> Defausse vide!");
			}
			//choix 4 pieux->colonne
			else if (menu.getChoix()==4) {
				String nomPieux;
				int indiceRecev;
				try {
					System.out.println("Entrez nom pieux expediteur : ");
					nomPieux = scan.next();
					System.out.println("Entrez numero colonne receveuse : ");
					indiceRecev= scan.nextInt()-1;
					fondation.deplacePieuxCol(fondation.convertNomToInd(nomPieux), tableau.getPaquetColonneVisible(indiceRecev), 
							itp, tableau.convertIndToEmp(indiceRecev), fondation.convertNomToEmp(nomPieux));
				
				} catch (DeplacementImpossibleException e) {
					System.out.println("Erreur -> Deplacement Impossible!");
				}
				catch (InputMismatchException e) {
					System.out.println("Erreur -> Saisi Incorrect!");
					scan.nextLine();
				}
				catch (SaisiIncorrectException e) {
					System.out.println("Erreur -> Saisi Incorrect!");
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Erreur -> Entrez un emplacement correct!");
				}
				catch(IndexOutOfBoundsException e) {
					System.out.println("Erreur -> Entrez un emplacement correct!");
				}
			}
			//choix 5 colonne->pieux
			else if (menu.getChoix()==5) {
				int indiceExp;
				String nomPieux;
				try {
				System.out.println("Entrez numero colonne expeditrice : ");
				indiceExp= scan.nextInt()-1;
				System.out.println("Entrez nom pieux receveur : ");
				nomPieux = scan.next();
					tableau.ColPieu(indiceExp, fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux)), itp, fondation.convertNomToEmp(nomPieux));
				} catch (DeplacementImpossibleException e) {
					System.out.println("Erreur -> Deplacement Impossible!");
				} catch (SaisiIncorrectException e) {
					System.out.println("Erreur -> Saisi Incorrect!");
				}
				catch (InputMismatchException e) {
					System.out.println("Erreur -> Saisi Incorrect!");
					scan.nextLine();
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Erreur -> Entrez un emplacement correct!");
				}
				catch(IndexOutOfBoundsException e) {
					System.out.println("Erreur -> Entrez un emplacement correct!");
				}			
			}
			//choix 6 colonne->colonne
			else if (menu.getChoix()==6) {
				int indiceExp, indiceRecev;
				try {
					System.out.println("Entrez numero colonne expeditrice : ");
					indiceExp= scan.nextInt()-1;
					System.out.println("Entrez numero colonne receveuse : ");
					indiceRecev= scan.nextInt()-1;
					tableau.deplaceColCol(indiceExp, indiceRecev, itp);
				} catch (DeplacementImpossibleException e) {
					System.out.println("Erreur -> Deplacement Impossible!");
				}
				catch (InputMismatchException e) {
					System.out.println("Erreur -> Saisi Incorrect!");
					scan.nextLine();
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Erreur -> Entrez un emplacement correct!");
				}
				catch(IndexOutOfBoundsException e) {
					System.out.println("Erreur -> Entrez un emplacement correct!");
				}
			}
			//choix 7 colonne(n)->colonne
			else if (menu.getChoix()==7) {
				int indiceExp, indiceRecev, nbCarte;
				try {
					System.out.println("Entrez numero colonne expeditrice : ");
					indiceExp= scan.nextInt()-1;
					System.out.println("Entrez numero colonne receveuse : ");
					indiceRecev= scan.nextInt()-1;
					System.out.println("Entrez le nombre de cartes a deplacer : ");
					//faire moins 1 pour tomber sur le bon indice de la ni�me carte
					nbCarte= scan.nextInt()-1;
					tableau.deplaceNcarteColCol(indiceExp, indiceRecev, nbCarte, itp);
				} catch (DeplacementImpossibleException e) {
					System.out.println("Erreur -> Deplacement Impossible!");
				}
				catch (InputMismatchException e) {
					System.out.println("Erreur -> Saisi Incorrect!");
					scan.nextLine();
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Erreur -> Entrez un emplacement correct!");
				}
				catch(IndexOutOfBoundsException e) {
					System.out.println("Erreur -> Entrez un emplacement correct!");
				}
			}
			//choix 8 recycler pioche
			else if (menu.getChoix()==8) {
				try {
					pioche.recyclePioche(pioche, itp);
				}
				catch (RecyclageErrorException e) {
					System.out.println("Erreur -> Recyclage impossible! Pioche encore pleine.");
				}
			}

			//ranger les cartes restantes automatiquement si configuration finale
			else if (menu.getChoix()==9) {
				if (ConfigFinale()) {
					System.out.println("Bravo! Vous avez terminez la partie.");
					ColPieuNfois();
					break;
				}
				else
					System.out.println("Vous n'êtes pas en configuration de fin!");
			}
			//choix 10 Fin
			else if (menu.getChoix()==10) {
				//fermeture fenêtre
				System.out.println("Fermeture de la fenêtre. A bientôt!");
				itp.ferme();
				break;
			}
			//re initialise choix utilisateur	
			System.out.println();
			menu.afficherMenu();
			menu.lireChoixUtilisateur();
		}
	}
}
