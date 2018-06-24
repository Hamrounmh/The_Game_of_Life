package projet_finale_couvr;

import java.io.*;

/**
 * Classe permattant de lire un fichier Lif. Elle a trois attributs  <br/>
 * Liste liste : qui va stocker les cellules vivantes ( presentees par des etoiles dans le fichier lif ) <br/>
 * Liste nbSurvie : qui va stocker les regles pour que les cellules vivantes survivent <br/>
 * Liste nbNaissance: qui va stocker les regles pour que les cellules mortes naissent.
 */
public class lecteurLif {


    private Liste liste = new Liste();
    private Liste<Integer> nbSurvie = new Liste<Integer>();
    private Liste<Integer> nbNaissance = new Liste<Integer>();

    /**
     * accesseur qui retourne une liste contenant les regles du jeu pour
     * qu'une cellule morte naisse
     * @return une liste de type Liste<Cellule>
     */
    public Liste<Integer> getNbNaissance() {
        return nbNaissance;
    }


    /**
     *
     * @return
     */
    public Liste<Integer> getNbSurvie() {
        return nbSurvie;
    }

    /**
     *Constructeur de la classe LecteurLif
     * @param chemin de type String
     * @throws IOException si le chemin du fichier lif est invalide
     */
    public lecteurLif(String chemin) throws IOException {

        BufferedReader f;
        f = new BufferedReader(new FileReader(chemin));

        String ligne;
        int x=0,y=0;

        while((ligne = f.readLine()) != null)
        {
            if(ligne.contains("#P")) {
                String[] tab=ligne.split(" ");
                x=Integer.valueOf(tab[1]);
                y=Integer.valueOf(tab[2]);

            }else if(ligne.contains("#R")){
                Liste<Integer> l = nbSurvie;
                for (char c : ligne.toCharArray()) {
                    if(Character.isDigit(c)) l.add((int)c - 48);
                    if(c == '/')  l =nbNaissance;
                }
            }else if(ligne.contains("#N") || ligne.contains("#D")){

            }else if(!ligne.contains("#")) {
                for(int i=0 ;i < ligne.length();i++)
                    if(ligne.charAt(i)=='*')
                        liste.add(new Cellule(x+i,y));
                y++;
            }
        }
        if(nbSurvie.isEmpty()) {
            nbSurvie.add(3).add(2);//regles par defaut
            nbNaissance.add(3);
        }
    }

    /**
     * accesseur qui retourne la liste contenat les cellules vivantes
     * dans le fichier lif ( les cellules presentees par des etoiles )
     * @return liste de type Liste
     */
    public Liste getListe() {
        return liste;
    }
}
