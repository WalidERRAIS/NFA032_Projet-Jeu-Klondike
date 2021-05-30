package tp_cartes;

import java.util.*;

public class MenuKlondike {
	private int choix;

	public void afficherMenu() {
		System.out.println("1. Piocher");
		System.out.println("2. defausse->colonne");
		System.out.println("3. defausse->pieux");
		System.out.println("4. colonne->pieux");
		System.out.println("5. colonne->colonne");
		System.out.println("6. colonne(n)->colonne");
		System.out.println("7. recycler pioche");
		System.out.println("8. Fin");
	}
	public int getChoix() {
		return this.choix;
	}
	public boolean choixUtilisateur() {
		if (this.choix<=8 || this.choix>=1)
			return true;
		else
			return false;
	}
	//méthode retourne choix utilisateur si compris entre 1 et 8
	public int lireChoixUtilisateur () {
		Scanner scan= new Scanner(System.in);
		boolean saisi= false;
		System.out.println("Votre choix : ");
		while (!saisi) {
			try {
				this.choix = scan.nextInt();
				scan.nextLine();
			}		
			catch (InputMismatchException e) {
				System.out.println("Incorrect. Entrez un nombre compris entre 1 et 8.");
				System.out.println("Votre choix : ");
				scan.nextLine();
				continue;
			}
			saisi=true;
		}
		while (this.choix<1 || this.choix>8) {
			System.out.println("Incorrect. Entrez un nombre compris entre 1 et 8.");
			System.out.println("Votre choix : ");
			this.choix = scan.nextInt();
		}
		return choix;
	}
}
