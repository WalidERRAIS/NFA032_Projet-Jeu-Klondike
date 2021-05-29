package tp_cartes;

public class Fondation {
	private PaquetPieux[] fondation= new PaquetPieux[4];
	public Fondation() {
		for(int i=0; i<4; i++) {
			fondation[i]= new PaquetPieux();
		}
	}
	//convertit nom paquet pieux recev en emplacement interface graphique
	public int convertNomToEmp(String nomPieux) {
		if (nomPieux.equalsIgnoreCase("A"))
			return 3;
		else if (nomPieux.equalsIgnoreCase("B"))
			return 4;
		else if (nomPieux.equalsIgnoreCase("C"))
			return 5;
		else if (nomPieux.equalsIgnoreCase("D"))
			return 6;
		else 
			throw new IllegalArgumentException();
	}
	//convertit nom paquet pieux recev en indice pour trouver paquet dans tableau fondation
	public int convertNomToInd(String nomPieux) {
		if (nomPieux.equalsIgnoreCase("A"))
			return 0;
		else if (nomPieux.equalsIgnoreCase("B"))
			return 1;
		else if (nomPieux.equalsIgnoreCase("C"))
			return 2;
		else if (nomPieux.equalsIgnoreCase("D"))
			return 3;
		else
			throw new IllegalArgumentException();
	}
	public PaquetPieux getPaquetPieux(int indicePaquetPieux) {
		return (fondation[indicePaquetPieux]);
	}
}
