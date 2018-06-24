package projet_finale_couvr;

/**
 * Classe permattant de gerer les cellules. Elle a deux attributs : <br/>
 * variable x de type int qui represente l'abscisse de la cellule <br/>
 * variable y de type int qui represente l'ordonnee de la cellule <br/>
 * Elle implemente l'interface Comparable.
 *
 */
public class Cellule implements Comparable<Cellule>{

	private int x,y;

	/**
	 * Constructeur de la classe Cellule. l'abscisse et l'ordonnee
	 * recoivent les valeurs x et y passees en parametres
	 * @param x de type int qui est le l'abscisse de la cellule
	 * @param y de type int qui est le l'ordonnee de la cellule
	 */
	public Cellule(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 *  accesseur qui retourne le l'abscisse de la cellule
	 * @return un entier qui est l'abscisse de la cellule
	 */
	public int getX() {
		return x;
	}

	/**
	 * accesseur qui retourne le l'ordonnee de la cellule
	 * @return un entier qui est l'ordonnee de la cellule
	 */
	public int getY() {
		return y;
	}

	/**
	 * mutateur qui prend en parametre un entier pour mettre
	 * a jour l'abscisse de la cellule
	 * @param x de type int
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * mutateur qui prend en parametres un entier pour mettre
	 * a jour l'ordonnee  de la cellule
	 * @param y de type int
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * methode qui compare la cellule avec la cellule passee en paramtres
	 * @param el de type Cellule
	 * @return un entier
	 * 0 si la cellule (les coordonnees)  est egal a la cellule pasee en parametres
	 * entier positif si la cellule ( les coordonnees) est plus grande que la cellule passee en parametres
	 * entier negatif si la cellule(les coordonnees)  est plus petite que la cellule passee en parametres
	 */
	public int compareTo(Cellule el) {
		if(y==el.getY())
			return x-el.getX();
		return y-el.getY();
	}

	/**
	 * methode qui compare la cellule avec l'objet passe en paramtres
	 * @param obj de type Object
	 * @return un boolean
	 * true si les deux cellules sont egaux ( les cordonnees )
	 * sinon elle retourne false
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cellule other = (Cellule) obj;
		if (this.x != other.x)
			return false;
		if (this.y != other.y)
			return false;
		return true;
	}

}
