package projet_finale_couvr;

import java.io.IOException;

/**
 * Classe permattant de gerer le monde ciculaire du jeu de la vie<br/>
 * Elle herite de la Classe Jeux_Fini.
 */
public class Jeux_Circulaire extends Jeux_Fini {


	/**
	 * Constructeur de la classe Jeux_circulaire
	 * @param chemin de type String
	 * @param x de type int
	 * @param y de type int
	 * @throws IOException si le chemin du fichier lif est invalide
	 */
	public Jeux_Circulaire(String chemin,int x,int y) throws IOException {
		super(chemin,x,y);
	}

	/**
	 * methode  qui prend en parametres les coordonnees dune cellule
	 * et qui retourne une liste contenant les cellules voisines selon
	 * les regles du monde circulaire
	 * @param x de type int qui represente l'abscisse de la cellule
	 * @param y de type int qui represente l'ordonnee de la cellule
	 * @return une liste de type Liste<Cellule> contenant les cellules voisines de la cellule qui a les
	 * coordonnes (x,y) passees en parametres
	 */
	public  Liste<Cellule> Entourage(int  x,int y) {
		int succ_x=x+1,prcd_x=x-1,succ_y=y+1,prcd_y=y-1;

		if(succ_x>limite_x/2) succ_x=-limite_x;
		if(prcd_y>limite_y/2) prcd_y=0;
		if(succ_y<0) succ_y=limite_y/2;
		if(prcd_x<-limite_x/2)prcd_x=limite_x/2;

		return (new Liste<Cellule>())
		.add(new Cellule(prcd_x, succ_y))
		.add(new Cellule(x, succ_y))
		.add(new Cellule(succ_x, succ_y))
		.add(new Cellule(prcd_x, y))
		.add(new Cellule(succ_x, y))
		.add(new Cellule(prcd_x, prcd_y))
		.add(new Cellule(succ_x, prcd_y))
		.add(new Cellule(x, prcd_y));

	}
}
