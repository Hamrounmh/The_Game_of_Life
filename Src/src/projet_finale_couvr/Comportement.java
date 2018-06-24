package projet_finale_couvr;

import java.io.IOException;
import java.lang.reflect.Type;
/**
 * Classe permattant de detecter le comportement du jeu de la vie et ses caracteristiques <br/>
 * Elle a (04) attributs : <br/>
 * jeu1 et jeu2 de type Jeux <br/>
 * resultat et chemin de type String.
 */


public class Comportement {

    private Jeux jeu1;
    private Jeux jeu2;
    private String resultat;
    private String chemin;


    /**
     * enumeration qui contient tous les types d'evolution du jeu
     */
    enum TypeComportement {
        Vaisseau,Stable,Mort,Periodique,Indeterminee
    }


    /**
     * Constructeur de la classe Comportement
     * @param chemin de type String
     * @param nbMaxRep de type int
     * @throws IOException si le chemin du fichier lif est invalide
     */
    public Comportement(String chemin,int nbMaxRep) throws IOException {
        this.chemin = chemin;
        jeu1 = new Jeux(chemin);
        jeu2 = new Jeux(chemin);
        jeu2.etat_suivant();
        switch (boucle(nbMaxRep))
        {
            case Vaisseau:
                Cellule d = new Cellule(0,0);
                int p = deplacement(d);
                resultat = "Vaisseau    (Taille de la queue = " + tailleQueue(p,d.getX(),d.getY())
                    + " , deplacement (" + d.getX() + "," + d.getY() + "))";break;
            case Stable:resultat="stable  (Taille de la queue = " + jeu1.getGeneration() + ")";break;
            case Mort:resultat="mort  (Taille de la queue = " + jeu1.getGeneration() + ")";break;
            case Indeterminee:resultat="Indeterminee";break;
            default:
                int t = periode();
                resultat="Periodique de periode : " + t + "   " +
                        "taille de la queue = "  + tailleQueue(t,0,0);
        }

    }

    /**
     *methode qui detecte le type d'evolution du jeu
     * pendant nMaxRep generation
     * @param nMaxRep de type int qui limite le nombre de generation pour donner les resultats
     * @return TypeComportement qui definit le type de comportement  du jeu de la vie pendant nMaxRep generation
     */
    private TypeComportement boucle(int nMaxRep)
    {
        for (int i=0;i<nMaxRep ;i++ ) {
            if (jeu1.getListe().isEmpty()) return TypeComportement.Mort;
            if (jeu1.getListe().size() == jeu2.getListe().size()) {
                if (jeu1.getListe().equals(jeu2.getListe())) {
                    if (jeu1.getGeneration() != jeu2.getGeneration())
                        if(periode() == 1) return TypeComportement.Stable;
                        else return TypeComportement.Periodique;
                    else return TypeComportement.Indeterminee;
                } else {
                    Maillon<Cellule> p1 = jeu1.getListe().getTete();
                    Maillon<Cellule> p2 = jeu2.getListe().getTete();
                    int dx = p1.getValeur().getX()
                            - p2.getValeur().getX();
                    int dy = p1.getValeur().getY()
                            - p2.getValeur().getY();


                    if (equals(jeu1.getListe(),jeu2.getListe(),dx,dy))
                        return TypeComportement.Vaisseau;
                }
            }
            jeu1.etat_suivant();
            if (jeu2.getGeneration() < nMaxRep) {
                jeu2.etat_suivant();
                jeu2.etat_suivant();
            }

        }
        return TypeComportement.Indeterminee;
    }

    /**
     * methode qui teste si deux liste ( deux configuration ) sont egaux avec un decalage x pour l'abscisse et
     * un decalage y pour l'ordonnee pour chaque cellule
     * @param l1 de type Liste<Cellule>
     * @param l2 de type Liste<Cellule>
     * @param dx de type int
     * @param dy de type int
     * @return un boolean true si les deux listes( configurations ) sont egaux sinon elle retourne false
     */
    private boolean equals(Liste<Cellule> l1,Liste<Cellule> l2,int dx, int dy)
    {
        Maillon<Cellule> p1 = l1.getTete();
        Maillon<Cellule> p2 = l2.getTete();
        while (p1 != null) {
            if (p1.getValeur().getX() == p2.getValeur().getX() + dx && p1.getValeur().getY() == p2.getValeur().getY() + dy) {
                p1 = p1.getSuivant();
                p2 = p2.getSuivant();
            } else return false;
        }
        return true;
    }

    /**
     *methode permattant de calculer la periode d'une configuration si elle est periodique
     * @return un entier qui indique la periode
     */
    private int periode()
    {
        Liste<Cellule> l = jeu2.getListe();
        jeu2.etat_suivant();
        int i = 1;
        for(;!l.equals(jeu2.getListe());i++) jeu2.etat_suivant();
        return i;
    }

    /**
     * methode qui prend une cellule en parametres et calculer son deplacement
     * @param d de type Cellule
     * @return un entier qui indique le deplacement de la cellule
     */
    private int deplacement(Cellule d)
    {
        Liste<Cellule> l = jeu2.getListe();
        jeu2.etat_suivant();
        d.setX(jeu2.getListe().getTete().getValeur().getX()-l.getTete().getValeur().getX());
        d.setY(jeu2.getListe().getTete().getValeur().getY()-l.getTete().getValeur().getY());
        int i = 1;
        for(;!equals(jeu2.getListe(),l,d.getX(),d.getY());i++) {

            jeu2.etat_suivant();
            d.setX(jeu2.getListe().getTete().getValeur().getX()-l.getTete().getValeur().getX());
            d.setY(jeu2.getListe().getTete().getValeur().getY()-l.getTete().getValeur().getY());
        }
        return i;
    }

    /**
     * methode qui prend la periode d'une configuration et les cordonnees d'une cellule et retourne la taille de la queue
     * @param periode de type int
     * @param dx de type int qui represente l'abscisse de la cellule
     * @param dy de type int qui represente l'ordonne de la cellule
     * @return la taille de la queue de de type int
     * @throws IOException si le chemin du fichier lif est invalide
     */
    private int tailleQueue(int periode,int dx,int dy) throws IOException
    {
        jeu1 = new Jeux(chemin);
        jeu2 = new Jeux(chemin);

        for(int i = 0;i<periode;i++)
            jeu2.etat_suivant();
        while(!equals(jeu2.getListe(),jeu1.getListe(),dx,dy))
        {
            jeu1.etat_suivant();
            jeu2.etat_suivant();
        }
        return jeu1.getGeneration();
    }



    /**
     * accesseur qui retourne le resultat obtenu
     * @return un String
     * qui contient le type de comportement et ses caracteristiques
     */

    public String getResultat() {
        return resultat;
    }

}
