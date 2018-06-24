package projet_finale_couvr;

import java.util.Objects;

/**
 * Classe permattant de gerer la liste chainee <br/>
 * Elle implemente l'interface Comparable<T>.
 * @param <T> de type generique
 */
public class Liste<T extends Comparable<T>> {

	private int longueur=0;
	private Maillon<T> tete= null;

	public Maillon<T> getTete() {
		return tete;
	}

	/**
	 * methode qui teste si une liste chainee est vide ou pas
	 * @return true si la liste est vide
	 * sinon elle retourne false
	 */
	public boolean isEmpty() {return tete == null;}

	/**
	 * methode qui ajoute un element de type T a la liste
	 * @param el de type T ( type generique )
	 * @return la liste qui contient les anciens element et l'element ajoute
	 */
	public Liste<T> add(T el) {
		if(!this.contains(el)) {
			longueur++;
			Maillon<T> p;
			Maillon<T> u;
			Maillon<T> x = new Maillon(el);



			if (this.tete == null || tete.getValeur().compareTo(el) > 0 ) {

				Maillon<T> m = new Maillon(el);
				m.setSuivant(tete);
				tete=m;
			} else {
				u=this.tete;
				p=tete.getSuivant();
				while(p!=null){
					if(p.getValeur().compareTo(el)>0)
						break;
					else {
						u=u.getSuivant();
						p=p.getSuivant();
					}
			     }
				u.setSuivant(x);
				x.setSuivant(p);
			}
		}
		return this;

	}

	/**
	 * methode qui teste si la liste contient un element ou pas
	 * @param el de type T
	 * @return return true si la liste  contient l'element sinon elle retourne false
	 */
	public boolean contains(T el) {

		Maillon m = this.tete;

		while (m != null) {
			if (m.getValeur().equals(el)) {
				return true;
			} else {
				m = m.getSuivant();
			}
		}
		return false;
	}
	/**
	 * methode pour calculer la taille de la liste
	 * @return return 0 si la liste est vide sinon  elle retourne la taille de la liste en utilisant la boucle while qui va
	 * du premier element de la liste jusqu'au dernier et a chaque fois on incremente le compteur nb
	 */
	public int size() {
		return longueur;
	}

	/**
	 * methode qui teste si deux listes sont egaux ( elle compare la liste avec l'objet passe en parametres )
	 * @param o de type Object
	 * @return un boolean. true si les deux liste contiennent les memes elements sinon elle retourne false
	 */
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if(((Liste<T>)o).size() != size()) return false;
		Maillon<T> p1 = ((Liste<T>) o).getTete();
		Maillon<T> p2 = getTete();
		while(p1!=null)
		{
			if(p1.getValeur().equals(p2.getValeur()))
			{
				p1=p1.getSuivant();
				p2=p2.getSuivant();
			}
			else return false;
		}
		return true;
	}



}
