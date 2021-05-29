package interfaceGraphique;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;

/**
* Emplacement de la fenêtre graphique du jeu de Klondike correspondant à un un tas dont on dévoile
* une seule carte: la pioche, la défausse, ou un pieu.
* Permet l'affichage d'une seule image de carte.
*/
public class UnTas extends JPanel{
	private ImageIcon image;
	protected InterfaceCarte ic;
	protected char ref;
	public UnTas(InterfaceCarte ic, char ref) {
		this.ic = ic;
		this.ref = ref;
		this.setPreferredSize(new Dimension(86,150));
		Font font = this.getFont();
		this.setFont(new Font(font.getFontName(),Font.BOLD, //font.getStyle(),
					font.getSize()*2));
	}
	public void paintComponent(Graphics g) {
		g.drawString(""+this.ref, 35, 20);	
		if (image == null) {
			g.setColor(Color.GREEN);
			g.fillRect(0, 30, 86, 120);
		}else
			image.paintIcon(this,g,0,30);
	}
	/**
	 * Fixe {@code carte} comme l'unique carte de ce tas, ou le vide si {@code carte} est {@code null}, puis re-affiche la fenêtre.
	 * @param carte carte à ajouter
	 */
	public void ajouterUneCarte(CarteAffichable carte) {
		if (carte==null)
			this.vider();
		else {
			image = ic.getIconFromName(carte.getNomDeFichierPNG());
			this.repaint();
		}
	}
	/**
	 * Fixe la dernière carte de {@code tab} comme l'unique carte de ce tas, ou le vide si {@code tab} ou si sa dernière carte est {@code null}, re-affiche ensuite la fenêtre. 
	 * @param tab tableau de cartes
	 */
	public void definirLesCartes(CarteAffichable[] tab) {
		if (tab==null || tab.length==0 || tab[tab.length-1]==null)
			this.vider();
		else {
			image = ic.getIconFromName(tab[tab.length-1].getNomDeFichierPNG());
			this.repaint();
		}
	}
	/**
	 * Vide ce tas, puis re-affiche la fenêtre.
	 * @param nb nombre de cartes à retirer.
	 */
	public void retirer(int nb) {
		this.vider();
	}
	/**
	 * Vide ce tas, puis re-affiche la fenêtre.
	 */
	public void vider() {
		image = null;
		this.repaint();
	}
	/**
	 * Fixe {@code carte} comme l'unique carte de ce tas, ou vide le tas si {@code carte} est {@code null}, puis re-affiche la fenêtre.
	 * @param carte
	 */
	public void definirCarte(CarteAffichable carte) {
		if (carte==null)
			this.vider();
		else {
			image = ic.getIconFromName(carte.getNomDeFichierPNG());
			this.repaint();
		}		
	}
	/**
	 * Fixe la dernière carte de {@code tab} comme l'unique carte de ce tas, ou le vide si {@code tab} ou si sa dernière carte est {@code null}, re-affiche ensuite la fenêtre. 
	 * @param tab tableau de cartes
	 */
	public void ajouterLesCartes(CarteAffichable[] tab) {
		this.definirLesCartes(tab);	
	}
}
