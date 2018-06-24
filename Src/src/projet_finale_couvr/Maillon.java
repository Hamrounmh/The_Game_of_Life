package projet_finale_couvr;

/**
 * Classe qui gere les maillons <br/>
 * Elle a deux attributs : <br/>
 * valeur de type T qui represente la valeur du maillon <br/>
 * suivant de type Maillon<T> qui represente le maillon suivant.
 * @param <T> de type generique.
 */
public class Maillon<T> {

    private T valeur;
    private Maillon<T> suivant=null;

    /**
     * Constructeur de la classe Maillon. Il construit un maillon
     * avec la valeur passee en parametres
     * @param valeur de type T
     */
    public Maillon(T valeur){

        this.valeur= valeur;
    }

    /**
     *mutateur qui met a jour l'attribut suivant par la valeur passee en parametres
     * @param suivant de type Maillon<T>
     */
    public void setSuivant(Maillon<T> suivant) {

        this.suivant = suivant;
    }

    /**
     * accesseur qui retourne le Maillon suivant ( attribut suivant )
     * @return suivant de type Maillon<T>
     */
    public Maillon<T> getSuivant() {

        return suivant;
    }

    /**
     * mutateur qui affecte la valeur passee en parametres a l'attribut valeur
     * @param valeur de type T
     */
    public void setValeur(T valeur) {
        this.valeur = valeur;
    }

    /**
     * accesseur qui retourne la valeur de l'attribut valeur
     * @return valeur de type T
     */
    public T getValeur() {
        return valeur;
    }
}
