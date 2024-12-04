package TP3;

import java.util.ArrayList;

public class HommeBete extends Creature{
    private String nomComplet; //Nom puis prénom
    private String sexe; //Tout choix possible
    private double poids; //En kilogramme
    private int taille; //En centimètre
    private int age;
    private int moralIndic; //Oscille entre 1 et 5
    private ArrayList<Maladie> listeMaladies;

    public HommeBete(String nomComplet, String sexe, double poids, int taille, int age, int moralIndic, ArrayList<Maladie> listeMaladies) {
        super(nomComplet, sexe, poids, taille, age, moralIndic, listeMaladies);
        this.nomComplet = nomComplet;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moralIndic = moralIndic;
    }

    @Override
    public int getMoralIndic() {
        return moralIndic;
    }

    public void setMoralIndic(int moralIndic) {
        this.moralIndic = moralIndic;
    }

    @Override
    public void hurler() {
        System.out.print("JE SUIS IDIOT !!!"); //Rapport au fait qu'il soit bête
    }

}
