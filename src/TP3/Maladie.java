package TP3;

/**
 * Représente une maladie dans le système de gestion de l'hôpital.
 * Une maladie peut être contagieuse ou létale.
 */
public class Maladie {
    private String nomComplet;
    private String nomAbrege;
    private int niveauActuel;
    private int niveauMaximum;
    private boolean contagieuse;


    /**
     * Constructeur pour créer une maladie.
     * @param nomComplet Nom complet de la maladie.
     * @param nomAbrege Nom abrégé de la maladie.
     * @param niveauActuel Niveau actuel de gravité.
     * @param niveauMaximum Niveau maximal de gravité.
     * @param contagieuse Indique si la maladie est contagieuse.
     */
    public Maladie(String nomComplet, String nomAbrege,int niveauActuel, int niveauMaximum, boolean contagieuse) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveauActuel = niveauActuel;
        this.niveauMaximum = niveauMaximum;
        this.contagieuse = contagieuse;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public String getNomAbrege() {
        return nomAbrege;
    }
    public void setNomAbrege(String nomAbrege) {
        this.nomAbrege = nomAbrege;
    }
    public int getNiveauActuel() {
        return niveauActuel;
    }
    public void setNiveauActuel(int niveauActuel) {
        this.niveauActuel = niveauActuel;
    }
    public int getNiveauMaximum() {
        return niveauMaximum;
    }
    public void setNiveauMaximum(int niveauMaximum) {
        this.niveauMaximum = niveauMaximum;
    }


    /**
     * Augmente le niveau de gravité de la maladie.
     * @param valeur Valeur à ajouter au niveau actuel.
     */
    public void augmenterNiveau(int valeur) {
        niveauActuel += valeur;
        if (niveauActuel > niveauMaximum) {
            niveauActuel = niveauMaximum;
        }
    }
    public boolean isContagieuse() {
        return contagieuse;
    }
    /**
     * Vérifie si la maladie est létale.
     * @return true si le niveau actuel est égal ou supérieur au niveau maximal.
     */
    public boolean estLetale() {
        return niveauActuel == niveauMaximum;
    }

    public void diminuerNiveau(int valeur) {
        niveauActuel -= valeur;
        if (niveauActuel <= 1) {
            niveauActuel = 1;
        }
    }

    @Override
    public String toString() {
        return nomAbrege + ": " + nomComplet;
    }
}
