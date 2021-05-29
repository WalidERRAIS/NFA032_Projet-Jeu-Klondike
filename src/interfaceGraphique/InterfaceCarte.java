package interfaceGraphique;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.awt.Graphics;

public class InterfaceCarte extends JFrame {
	private HashMap<String,ImageIcon> cartes = new HashMap<String,ImageIcon>();
	private JPanel content = new JPanel(new SpringLayout());
	private int nbCols, nbLigs;
	protected Font font;
	public InterfaceCarte(int nbCols, int nbLigs) {
		this.nbCols = nbCols;
		this.nbLigs = nbLigs;
		String[] vals = {"as", "deux", "trois","quatre", "cinq", "six","sept","huit","neuf",
				"dix", "valet", "dame", "roi"};
		String[] couls = {"pique", "coeur", "carreau", "trefle"};
		Font font;
		for(String val: vals)
			for (String coul: couls) {
				String st = val + "_de_" + coul + ".png";
				cartes.put(st, new ImageIcon("cards/" + st));
			}
		cartes.put("dos.png",new ImageIcon("cards/dos.png"));
		content.setBackground(Color.green);
		font = content.getFont();
		content.setFont(new Font(font.getFontName(),Font.BOLD, //font.getStyle(),
					font.getSize()*2));
		this.setContentPane(content);
	}
	public void addComponent(JComponent comp) {
		this.getContentPane().add(comp);
	}
	public ImageIcon getIconFromName(String st) {
		return cartes.get(st);
	}
	public void makeGrid() {
		SpringUtilities.makeCompactGrid(content, nbLigs, nbCols, 10,
				10, 10, 10);
	}
}
