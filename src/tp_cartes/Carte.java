package tp_cartes;
import interfaceGraphique.*;

public class Carte implements CarteAffichable{
	//attributs
	private boolean visibilite=false;
	private String nomfich;
	private Valeur valeur;
	private Couleur couleur;
	private Teinte teinte;
	//constructeur
	public Carte(Valeur v, Couleur c, String n) {
		this.nomfich = n;
		this.valeur = v;
		this.couleur = c;
		//attribue teinte à la carte selon sa couleur
		if (this.couleur.equals(Couleur.carreau) || this.couleur.equals(Couleur.coeur))
			this.teinte = Teinte.Rouge;
		else
			this.teinte = Teinte.Noir;
	}
	//constructeur pour dos de carte
	public Carte(String n) {
		this.nomfich= n;
	}
	@Override
	public String getNomDeFichierPNG() {
		//afficher carte si visibilite = true
		if (!visibilite)
			return "dos.png";
		else
			return nomfich;
	}
	public void rendVisible() {
		visibilite=true;
	}
	public void rendCache() {
		visibilite=false;
	}
	public boolean getVisibilite() {
		return visibilite;
	}
	public Valeur getValeur() {
		return valeur;
	}
	public Couleur getCouleur() {
		return couleur;
	}
	public Teinte getTeinte() {
		return teinte;
	}
	//retourne vrai si les deux cartes en param => meme couleur et faux...
	public boolean memeCouleur(Carte ajouter, Carte sommetPieu) {
		return (ajouter.getCouleur().equals(sommetPieu.getCouleur()));
	}
	//retourne vrai si la carte en param est la suivante de this dans Valeur
	public boolean valeurSuivante(Carte ajouter) {
		return (ajouter.getValeur().ordinal()==this.getValeur().ordinal()+1);
	}
	//retourne vrai si la carte en param est la suivante et meme couleur que this carte
	public boolean precedeMemeCouleur(Carte ajouter) {
		return ((this.valeurSuivante(ajouter)) && (memeCouleur(ajouter,this)));
	}
	//retourne vrai si la carte en param est la precedente de this dans Valeur
		public boolean valeurPrecedente(Carte ajouter) {
			return (ajouter.getValeur().ordinal()==this.getValeur().ordinal()-1);
		}
	//retourne vrai si les deux cartes en param => teinte diff et faux si ...
	public boolean memeTeinte(Carte a, Carte b) {
		return (!a.teinte.equals(b.teinte));
	}
	//retourne vrai si la carte en param est la precedente de this et teinte alterne
	public boolean precedeDiffTeinte(Carte ajouter) {
		return ((this.valeurPrecedente(ajouter)) && (memeTeinte(ajouter,this)));
	}
	public String toString() {
		return this.valeur+" de "+this.couleur; 
	}
	
}
