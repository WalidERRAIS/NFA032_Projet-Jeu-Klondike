package tp_cartes;
import java.util.Scanner;

import interfaceGraphique.*;

public class TestCarte {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		MenuKlondike menu= new MenuKlondike();
		InterfaceKlondike itp = new InterfaceKlondike();
		PaquetDistributeur p = new PaquetDistributeur();
//		p.melangePaquet();
		TableauKlondike tableau= new TableauKlondike(p, itp);
		Fondation fondation = new Fondation(); 
		Pioche pioche = new Pioche(p, itp);
		
		//Menu choix
		menu.afficherMenu();
		menu.setChoix();
		while(menu.choixUtilisateur()) {
			if(menu.getChoix()== 1)
				pioche.deplaceCartePiocheDefausse(pioche, itp);
			else if (menu.getChoix()==2) {
				int indiceRecev;
				System.out.println("Entrez numero colonne receveuse : ");
				indiceRecev= scan.nextInt()-1;
				pioche.deplaceDefausseCol(tableau.getPaquetColonneVisible(indiceRecev), itp, tableau.convertIndToEmp(indiceRecev));
			}
			else if (menu.getChoix()==3) {
				String nomPieux;
				System.out.println("Entrez nom pieux receveur : ");
				nomPieux = scan.next();
				pioche.deplaceDefaussePieu(fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux)), itp, fondation.convertNomToEmp(nomPieux));
			}
			else if (menu.getChoix()==4) {
				int indiceExp;
				String nomPieux;
				System.out.println("Entrez numero colonne expediteuse : ");
				indiceExp= scan.nextInt()-1;
				System.out.println("Entrez nom pieux receveur : ");
				nomPieux = scan.next();
				tableau.ColPieu(indiceExp, fondation.getPaquetPieux(fondation.convertNomToInd(nomPieux)), itp, fondation.convertNomToEmp(nomPieux));
			}
			else if (menu.getChoix()==5) {
				int indiceExp, indiceRecev;
				System.out.println("Entrez numero colonne expediteuse : ");
				indiceExp= scan.nextInt()-1;
				System.out.println("Entrez numero colonne receveuse : ");
				indiceRecev= scan.nextInt()-1;
				tableau.deplaceColCol(indiceExp, indiceRecev, itp);
			}
			else if (menu.getChoix()==6) {
				int indiceExp, indiceRecev, nbCarte;
				System.out.println("Entrez numero colonne expediteuse : ");
				indiceExp= scan.nextInt()-1;
				System.out.println("Entrez le nombre de cartes a deplacer : ");
				//faire moins 1 pour tomber sur le bon indice de la nième carte
				nbCarte= scan.nextInt()-1;
				System.out.println("Entrez numero colonne receveuse : ");
				indiceRecev= scan.nextInt()-1;
				tableau.deplaceNcarteColCol(indiceExp, indiceRecev, nbCarte, itp);
			}
			else if (menu.getChoix()==7)
				pioche.recyclePioche(pioche, itp);
			else if (menu.getChoix()==8) {
				//fermeture fenêtre
				System.out.println("Fermeture de la fenetre.");
				itp.ferme();
			}
			//re initialise choix utilisateur	
			System.out.println();
			menu.afficherMenu();
			menu.setChoix();
		}

	}

}
