package projet_finale_couvr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



	/**
 	* Classe permattant de gerer le monde infini du jeu de la vie <br/>
 	* Elle a (06) attributs : <br/>
 	* Liste<Cellule> liste : contient  les cellules vivantes </br>
 	* Liste<Integer> nbSurvie : contient les regles du jeu pour que les cellules vivantes survivent <br/>
	 * Liste<Integer> nbNaissance : contient  les regles du jeu pour que les cellules mortes  naissent <br/>
	 * int minX : qui stocke l'abscisse  qui a la valeur minimale <br/>
	 * int minY : qui stocke l'ordonnee  qui a la valeur minimale <br/>
	 * int generation : qui stocke le numero de la generation du jeu.
 	*/
public class Jeux {
	private Liste<Cellule> liste;
	private Liste<Integer> nbSurvie;
	private Liste<Integer> nbNaissance;
	private int minX;
	private int minY;
	private int generation=0;

	/**
	 *Constructeur de la class Jeux. Il construit un Jeux a partir
	 * un fichier lif passe en parametres et chaque attribut recoit la valeur qui lui correspond
	 * @param chemin de type string qui est le chemin du fichier lif
	 * @throws IOException si le chemin du fichier lif est invalide
	 */

	public Jeux(String chemin) throws IOException {
		lecteurLif lf = new lecteurLif(chemin);
		this.liste = lf.getListe();
		this.minX=getMinX();
		this.minY=liste.getTete().getValeur().getY();
		this.nbSurvie = lf.getNbSurvie();
		this.nbNaissance = lf.getNbNaissance();
	}
	/**
	 * methode pour incrementer le numero de la generation quand on passe a l'etat suivante
	 */

	public void incGeneration(){
		generation++;
	}
	/**
	 * accesseur qui retourne l'abscisse  qui a la valeur minimale dans la liste
	 * @return l'abscisse  qui a la valeur minimale dans la liste
	 */

	public int getMinX() {
		Maillon<Cellule> p=this.liste.getTete();
		int x=0;
		if(p!=null)  x=p.getValeur().getX();
		while(p!=null) {
			if(p.getValeur().getX()<x) x=p.getValeur().getX();
			p=p.getSuivant();
		}

		return x;
	}

	/**
	 * accesseur qui retourne le numero de la generation
	 * @return le numero de la génération
	 */

	public int getGeneration(){
		return generation;
	}

	/**
	 * methode qui transforme la liste des cellules vivantes a un String qui les represente par des etoiles
	 * en prenant en compte leur coordonnees. Ce String contient aussi le numero de la generation du jeu de la vie.
	 * @return un string qui contient l'affichage du jeu et le numero de la generation <br/>
	 * une cellule vivante est representee par une etoile
	 */

	public String toString() {
		String str="";
		if(liste != null) {
			int x=(getMinX()>minX)? minX: getMinX();

			int y=(liste.getTete().getValeur().getY()>minY)? minY : liste.getTete().getValeur().getY();
			Maillon<Cellule> p = liste.getTete();

			while(p!=null) {

				for(int i=y;i<p.getValeur().getY();i++) {
					str+="\n";
					x=(getMinX()>minX)? minX: getMinX();
				}
				y=p.getValeur().getY();
				for(int i=x;i<p.getValeur().getX();i++) {
					str+="-";

				}
				x=p.getValeur().getX() + 1;
				str+="*";
				p=p.getSuivant();


			}
		}

		return "+++++Generation n°" + this.generation + "+++++\n" + str;
	}

	/**
	 * methode qui  prend en parametre les coordonnees  d'une cellule
	 * @param x de type int qui represente l'abscisse de la cellule
	 * @param y de type int qui represente l'ordonnee de la cellule
	 * @return le nombre de voisins de la cellule qui a les coordonnes (x,y)
	 */

	public int nbVoisin(int x, int y) {
		int nb=0;
		Maillon<Cellule> p = liste.getTete();
		while(p!=null){
			if(Math.abs(p.getValeur().getY()-y)<2)
				if(Math.abs(p.getValeur().getX()-x) <2)
					if(p.getValeur().getX()!=x || p.getValeur().getY()!=y)
						nb++;
			p=p.getSuivant();
		}

		return nb;
	}



	/**
	 * methode qui  prend en paramtres les coordonnees  d'une cellule et retourne
	 * la liste qui contient les cellules  voisines
	 * @param x de type int qui est l'abscisse de la cellule
	 * @param y de type int qui est l'ordonnee de la cellule
	 * @return une liste qui contient les cellules voisines
	 * de la cellule qui a les coordonnees (x,y) passees en parametres
	 */


	public Liste Entourage(int  x,int y) {
		return (new Liste()).add(new Cellule(x+1,y+1))
				.add(new Cellule(x,y+1))
				.add(new Cellule(x-1,y+1))
				.add(new Cellule(x+1,y))
				.add(new Cellule(x-1,y))
				.add(new Cellule(x+1,y-1))
				.add(new Cellule(x,y-1))
				.add(new Cellule(x-1,y-1));
	}

	/**
	 * methode qui produit la generation suivante du jeu
	 * de la vie selon les regles du monde infini
	 */
	public void etat_suivant() {
		Liste <Cellule>nouvelleListe = new Liste<Cellule>();
		incGeneration();
		int x;
		int y;

		Maillon<Cellule> p = getListe().getTete();
		while(p!=null)
		{
			x=p.getValeur().getX();
			y=p.getValeur().getY();

			int nb = nbVoisin(x,y);
			boolean survie = false;
			Maillon<Integer> i = nbSurvie.getTete();
			while(i!=null) {survie = survie || (i.getValeur()==nb);i=i.getSuivant();}
			if(survie)
				nouvelleListe.add(new Cellule(x, y));

			Liste<Cellule> li=Entourage(x, y);
			Maillon<Cellule> m = li.getTete();
			while(m!=null)
			{
				x=m.getValeur().getX();
				y=m.getValeur().getY();
				boolean naissance = false;
				i = nbNaissance.getTete();
				nb = nbVoisin(x,y);
				while(i!=null) {naissance = naissance || (i.getValeur()==nb);i=i.getSuivant();}
				if(naissance && !liste.contains(m.getValeur())) {
					nouvelleListe.add(new Cellule(x,y));

					//amelioration possible
				}
				m=m.getSuivant();
			}
			p=p.getSuivant();
		}


		SetListe(nouvelleListe);
		this.liste = nouvelleListe;
	}
	/**
	 * accesseur qui retourne une liste qui contient les cellules vivantes du jeu de la vie
	 * @return une liste de type Liste<Cellule>
	 */

	public Liste<Cellule> getListe() {
		return liste;
	}

	/**
	 * mutateur qui met a jour la liste avec la liste passee en parametres
	 * @param l de type Liste<Cellule>
	 */

	public void SetListe(Liste<Cellule> l ){
		this.liste=l;
	}


}
