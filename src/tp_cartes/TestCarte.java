package tp_cartes;

import gestionError.*;

public class TestCarte {
	public static void main(String[] args) throws DeplacementImpossibleException, SaisiIncorrectException {
		PlateauJeu partie= new PlateauJeu();
		partie.Jouer();
	}

}
