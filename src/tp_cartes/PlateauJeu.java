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
	public void Jouer() throws DeplacementImpossibleException, SaisiIncorrectException {
		//Menu choix
		menu.afficherMenu();
		menu.lireChoixUtilisateur();
		while (menu.choixUtilisateur()) {
			//choix 1
			if (menu.getChoix()== 1) { 
				try {
					pioche.deplaceCartePiocheDefausse(pioche, itp);
				}
				catch (PiocheVideException e) {
					System.out.println("Erreur -> Pioche vide!");
				}
			}
			//choix 2
			else if (menu.getChoix()==2) {
				int indiceRecev;
				System.out.println("Entrez numero colonne receveuse : ");
				try {
					indiceRecev= scan.nextInt()-1;
					pioche.deplaceDefausseCol(tableau.getPaquetColonneVisible(indiceRecev), itp, tableau.convertIndToEmp(indiceRecev));
				} catch (DeplacementImpossibleException e) {
					System.out.println("Erreur -> Deplacement Impossible!");
				}
				catch (InputMismatchException e) {
					System.out.println("Erreur -> Saisi Incorrect!");
					scan.nextLine();
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Erreur -> Saisi Incorrect!");
				}
			}
			//choix 3
			else if (menu.getChoix()==3) {
				String nomPieux;
				System.out.println("Entrez nom pieux receveur : ");
				try {
					nomPieux = scan.next();
					pioche.deplaceDefaussePieu(fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux)), itp, fondation.convertNomToEmp(nomPieux));
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
			}
			//choix 4
			else if (menu.getChoix()==4) {
				int indiceExp;
				String nomPieux;
				System.out.println("Entrez numero colonne expeditrice : ");
				try {
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
					System.out.println("Erreur -> Saisi Incorrect!");
				}
			}
			//choix 5
			else if (menu.getChoix()==5) {
				int indiceExp, indiceRecev;
				System.out.println("Entrez numero colonne expeditrice : ");
				try {
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
					System.out.println("Erreur -> Saisi Incorrect!");
				}
			}
			//choix 6
			else if (menu.getChoix()==6) {
				int indiceExp, indiceRecev, nbCarte;
				System.out.println("Entrez numero colonne expeditrice : ");
				try {
					indiceExp= scan.nextInt()-1;
					System.out.println("Entrez numero colonne receveuse : ");
					indiceRecev= scan.nextInt()-1;
					System.out.println("Entrez le nombre de cartes a deplacer : ");
					//faire moins 1 pour tomber sur le bon indice de la nième carte
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
					System.out.println("Erreur -> Saisi Incorrect!");
				}
			}
			//choix 7
			else if (menu.getChoix()==7) {
				try {
					pioche.recyclePioche(pioche, itp);
				}
				catch (RecyclageErrorException e) {
					System.out.println("Erreur -> Recyclage impossible! Pioche encore pleine.");
				}
			}
			//choix 8
		 	if (menu.getChoix()==8) {
				//fermeture fenêtre
				System.out.println("Fermeture de la fenetre.");
				itp.ferme();
			}
			//re initialise choix utilisateur	
			System.out.println();
			menu.afficherMenu();
			menu.lireChoixUtilisateur();
		}
	}
}
