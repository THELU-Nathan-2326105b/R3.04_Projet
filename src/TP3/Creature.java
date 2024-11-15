package TP3;

import java.util.ArrayList;

public abstract class Creature {
    private String nomComplet; //Nom puis prénom
    private String sexe; //Tout choix possible
    private double poids; //En kilogramme
    private int taille; //En centimètre
    private int age;
    private int moralIndic; //Oscille entre 5 et 1
    //private ArrayList<Maladie> listeMaladies;

    public Creature(String nomComplet, String sexe, double poids, int taille, int age, int moralIndic/*, ArrayList<Maladie> listeMaladies*/) {
        this.nomComplet = nomComplet;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moralIndic = moralIndic;
        //this.listeMaladies = listeMaladies;
    }

    public void setMoralIndic(int moralIndic) {
        this.moralIndic = moralIndic;
    }

    public int getMoralIndic() {
        return moralIndic;
    }

    public void attendre(){
        if(this.moralIndic<=1){
            this.hurler();
        }
        else{
            this.setMoralIndic(this.moralIndic-1);
        }
    }

    public void hurler(){
        System.out.print("Agrougrou");
    }

    /*public void sEmporter(){

    }*/
}
