package TP3;

public class Maladie {
    private String nomComplet;
    private String nomAbrege;
    private int niveauActuel;
    private int niveauMaximum;

    public Maladie(String nomComplet, String nomAbrege,int niveauActuel, int niveauMaximum) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveauActuel = niveauActuel;
        this.niveauMaximum = niveauMaximum;
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
    public void augmenterNiveau(int valeur) {
        niveauActuel += valeur;
        if (niveauActuel > niveauMaximum) {
            niveauActuel = niveauMaximum;
        }
    }
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
