package interfaceGraphique;
import java.awt.*;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.Font;

/**
 * Interface graphique permettant l'affichage des tas de cartes du jeu de Klondike.
 * A la création, un plateau vert est affiché avec 14 emplacements correspondant aux tas des cartes du jeu.
 * Dans la fenêtre, les tas sont signalés par une étiquette : P (pioche), A, B, C, D (les pieux), 1 à 7 (les colonnes).
 * Les cartes peuvent être ajoutés ou retirés des différents tas via les indices suivants :
 * 0, (pioche), 3 à 6 (pieux), 7 à 13 (colonnes). Les tas d'indices 0 à 6 ne peuvent recevoir ni afficher
 * plus d'une carte. Dans les autres tas, on peut afficher jusqu'à 13 cartes.
 */

public class InterfaceKlondike{
	
	private static final long serialVersionUID = 1L;
	private InterfaceCarte ic = new InterfaceCarte(7,2);
	private UnTas[] composant = new UnTas[14];
//	public Font font;
	/**
	 * Affiche la fenêtre graphique avec tous les tas vides.
	 */
	public InterfaceKlondike() {
		char[] idents = {' ','P',' ','A','B','C','D','1','2','3',
				'4','5','6','7'};
		char car = '1';
		for (int i=0; i<7; i++) {
			composant[i]=new UnTas(ic,idents[i]);
			ic.addComponent(composant[i]);
		}
		for (int i=7; i<14; i++) {
			composant[i]=new UneColonne(ic,idents[i]);
			ic.addComponent(composant[i]);
		}
		ic.makeGrid();
		ic.pack();
		ic.setVisible(true);
//		changeFont();
	}
	/**
	 * Retourne true si idx est l'indice d'un pieu (compris entre 3 et 6), false sinon.
	 * @param idx indice à tester
	 */
	public boolean estIndexPieu(int idx) {
		return (idx>=3 && idx <=6);
	}
	/**
	 * Retourne true si idx est l'indice d'un tas de la fenêtre graphique (de 0 à 13), false sinon.
	 * @param idx indice à tester
	 */
	public boolean estIndexIG(int idx) {
		return (idx>=0 && idx <= composant.length);
	}
	/**
	 * Retourne true si idx est l'indice d'un tas d'une colonne (de 7 à 13), false sinon.
	 * @param idx indice à tester
	 */
	public boolean estIndexIGColonne(int idx) {
		return (idx>=7 && idx <= composant.length-1);
	}
	/**
	 * Si {@code num} est compris entre 0 et 6, vide le tas d'indice {@code num} si {@code carte} 
	 * est {@code null} et sinon fixe {@code carte} comme l'unique carte du tas {@code num}.
	 * Si {@code num} est compris entre 7 et 13 et {@code carte} non null, ajoute cette carte dans le tas {@code num}. 
	 * Re-affiche la fenêtre.
	 * @throws ArrayIndexOutOfBoundsException si num n'est pas compris entre 0 et 13.
	 * @param carte carte à ajouter 
	 * @param num indice du tas d'ajout
	 */
	public void ajouterUneCarte(CarteAffichable carte, int num) {
		composant[num].ajouterUneCarte(carte);
	}
	/**
	 * Si {@code num} est compris entre 0 et 6, vide le tas d'indice {@code num} si {@code tab} ou si sa dernière carte sont {@code null} ou si {@code tab} est vide. Sinon, 
	 * fixe la dernière carte de {@code tab} comme l'unique carte du tas d'indice {@code num}.
	 * Si {@code num} est compris entre 7 et 13, le contenu du tas d'indice {@code num} est redéfini par les cartes du tableau {@code tab}. 
	 * Re-affiche la fenêtre.
	 * @throws ArrayIndexOutOfBoundsException si num n'est pas compris entre 0 et 13.
	 * @param tab tableau de cartes à ajouter
	 * @param num indice du tas d'ajout
	 */
	public void definirLesCartes(CarteAffichable[] tab, int num) {
		composant[num].definirLesCartes(tab);
	}
	/**
	 * Si {@code num} est compris entre 0 et 6, vide le tas d'indice {@code num}. 
	 * Si {@code num} est compris entre 7 et 13, retire nb cartes du tas d'indice {@code num}.
	 * Re-affiche la fenêtre.
	 * @throws ArrayIndexOutOfBoundsException si num n'est pas compris entre 0 et 13.
	 * @throws IndexOutOfBoundsException si num est compris
	 * entre 7 et 13 et qu'il y a moins de nb cartes dans le tas d'indice num.
	 * @param nb
	 * @param num
	 */
	public void retirerDesCartes(int nb, int num) {
		composant[num].retirer(nb);
	}
	/**
	 * Vide le tas d'indice num et re-affiche la fenêtre.
	 * @throws ArrayIndexOutOfBoundsException si num n'est pas compris entre 0 et 13.
	 * @param num
	 */
	public void vider(int num) {
		composant[num].vider();
	}
	/**
	 * Si {@code carte} égale à null vide le tas d'indice {@code num}. Sinon, définit {@code carte} comme l'unique carte du 
	 * tas d'indice {@code num}. 
	 * Re-affiche la fenêtre.
	 * @throws ArrayIndexOutOfBoundsException si num n'est pas compris entre 0 et 13.
	 * @param carte carte à ajouter
	 * @param num indice du tas d'ajout
	 */
	public void definirCarte(CarteAffichable carte, int num) {
		composant[num].definirCarte(carte);
	}
	/**
	 * Ferme la fenêtre graphique.
	 */
	public void ferme() {
		ic.dispose();
	}
	/**
	 * Si {@code num} est compris entre 0 et 6, vide le tas d'indice {@code num} lorsque {@code tab} ou sa dernière carte sont égales à {@code null}, ou si
	 * {@code tab} est vide. Sinon, 
	 * fixe la dernière carte de {@code tab} comme l'unique carte du tas d'indice {@code num}.
	 * Si {@code num} est compris entre 7 et 13, ajoute au tas d'indice {@code num} les cartes du tableau {@code tab}.
	 * Re-affiche la fenêtre.
	 * @throws ArrayIndexOutOfBoundsException si num n'est pas compris entre 0 et 13.
	 * @param tab tableau de cartes à ajouter
	 * @param num indice du tas d'ajout
	 */
	public void ajouterLesCartes(CarteAffichable[] tab, int num) {
		composant[num].ajouterLesCartes(tab);
	}
}
