package projet_finale_couvr;

import java.io.IOException;

/**
 * Classe permattant de gerer le monde fini du jeu de la vie<br/>
 * Elle herite de la classe Jeux et a deux attributs de plus : <br/>
 * limite_x : de type int qui limite l'ordonne  de la cellule maximal qui peut etre contenu dans la liste  <br/>
 * limite_y : de type int qui limite l'abscisse de la cellule maximal qui peut etre contenu dans la liste.
 */
public class Jeux_Fini extends Jeux {
    protected int limite_x,limite_y;

	/**
	 * Constructeur de la classe Jeux_Fini. Il construit un Jeu_Fini a partir un fichier lif
	 * avec les limites limite_x et limite_y sur les coordonnes des cellules qui peuvent etre acceptees dans la liste
	 * @param chemin de type String
	 * @param limite_x de type int
	 * @param limite_y de type int
	 * @throws IOException si le chemin du fichie lif est invalide
	 */
    public Jeux_Fini (String chemin,int limite_x,int limite_y)throws IOException{
        super(chemin);
        this.limite_x=limite_x;
        this.limite_y=limite_y;
    }

	/** methode qui genere l'etat suivante
	 * du jeu de la vie selon les regles du monde fini
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
          if(x<limite_x/2 && x>-limite_x/2 && y<limite_y){
              int nb = nbVoisin(x,y);
              if(nb ==2 ||  nb ==3)
                  nouvelleListe.add(new Cellule(x, y));
          }
			Liste<Cellule> li=Entourage(x, y);
			Maillon<Cellule> m = li.getTete();
			while(m!=null)
			{
				x=m.getValeur().getX();
				y=m.getValeur().getY();
                if(x<limite_x/2 && x>-limite_x/2 && y<limite_y){
                    if(nbVoisin(x,y)==3 && !getListe().contains(m.getValeur())) {
                        nouvelleListe.add(new Cellule(x,y));
                    }
                }
				m=m.getSuivant();
			}
			p=p.getSuivant();
		}
		SetListe(nouvelleListe);
	}
}
