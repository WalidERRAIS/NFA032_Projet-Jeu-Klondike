package tp_cartes;

import java.util.Scanner;

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
	public void setChoix() {
		System.out.println("Votre choix : ");
		Scanner scan= new Scanner(System.in);
		this.choix = scan.nextInt();
	}
	public boolean choixUtilisateur() {
		if (this.choix<=8 || this.choix>=1)
			return true;
		else
			return false;
	}
}
