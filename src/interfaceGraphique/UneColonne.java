package interfaceGraphique;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

/**
 * Emplacement de la fenêtre graphique du jeu de Klondike correspondant à une colonne.
 * Permet l'affichage, l'ajout, le retrait de cartes affichables d'une liste et leur affichage.
 */
public class UneColonne extends UnTas {
	private ArrayList<ImageIcon> tab;

	public UneColonne(InterfaceCarte ic, char ref) {
		super(ic,ref);
		tab = new ArrayList<ImageIcon>();
		this.setPreferredSize(new Dimension(86,120+(25*20)));
		Font font = this.getFont();
		font = this.getFont();
	}
	/**
	 * Ajout d'une carte non nulle dans ce tas de cartes et re-affichage de la fenêtre.
	 */
	public void ajouterUneCarte(CarteAffichable carte) {
		if (carte != null) {
			tab.add(ic.getIconFromName(carte.getNomDeFichierPNG()));
			this.repaint();
		}
	}
	/**
	 *  Le contenu du tas est redéfini par les cartes du tableau {@code tab}, puis re-affiche la fenêtre.
	 */
	public void definirLesCartes(CarteAffichable[] tab) {
		this.tab.clear();
		for (CarteAffichable carte: tab)
			this.tab.
			add(ic.getIconFromName(
					carte.getNomDeFichierPNG()));
		this.repaint();
	}
	/**
	 * Ajoute dans ce tas les cartes du tableau {@code tab}, puis re-affiche la fenêtre.
	 */
	public void ajouterLesCartes(CarteAffichable[] tab) {
		for (CarteAffichable carte: tab)
			this.tab.
			add(ic.getIconFromName(
					carte.getNomDeFichierPNG()));
		this.repaint();
	}
    /**
     * Retire les {@code nb} dernières cartes ajoutées au tas  puis re-affiche la fenêtre.
     */
	public void retirer(int nb) {
		for (int i=0; i<nb; i++) {
			tab.remove(tab.size()-1);
		}
		this.repaint();			
	}
	/**
     * Vide ce tas puis re-affiche la fenêtre.
     */
	public void vider() {
		tab.clear();
		this.repaint();
	}
	/**
     * Vide le tas, ajoute la carte c puis re-affiche la fenêtre.
     */
	public void definirCarte(CarteAffichable carte) {
		if (carte==null)
			this.vider();
		else {
			tab.clear();
			tab.add(ic.getIconFromName(carte.getNomDeFichierPNG()));
			this.repaint();
		}		
	}

	public void paintComponent(Graphics g) {
		int y;
		if (tab.isEmpty()) 
			y=30;
		else
			y = 30+tab.size()*25+95;		
		g.drawString(""+this.ref, 35, 20);	
		for (int i=0; i<tab.size(); i++) {
			tab.get(i).paintIcon(this,g,0,30+i*25);	
		}
		g.setColor(Color.GREEN);
		g.fillRect(0, y, 86, 120+(25*20)-y);
	}

}
