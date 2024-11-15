package TP3;

import java.util.ArrayList;
import java.util.Random;

public abstract class Creature {
    private String nomComplet; //Nom puis prénom
    private String sexe; //Tout choix possible
    private double poids; //En kilogramme
    private int taille; //En centimètre
    private int age;
    private int moralIndic; //Oscille entre 5 et 1
    private ArrayList<Maladie> listeMaladies;

    public Creature(String nomComplet, String sexe, double poids, int taille, int age, int moralIndic, ArrayList<Maladie> listeMaladies) {
        this.nomComplet = nomComplet;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moralIndic = moralIndic;
        this.listeMaladies = listeMaladies;
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

    public ArrayList<Maladie> getListeMaladies() {
        return listeMaladies;
    }

    public void tomberMalade() {
        Random r1 = new Random();
        int nb = r1.nextInt(6);
        if (nb != 0){
            int nMal= r1.nextInt(6);
            switch(nMal){
                case(0):
                    Maladie m0 =new Maladie("Maladie débilitante chronique", "MDC", 1, 5);
                    this.listeMaladies.add(m0);
                case(1):
                    Maladie m1 =new Maladie("Syndrome fear of missing out", "FOMO", 1, 5);
                    this.listeMaladies.add(m1);
                case(2):
                    Maladie m2 =new Maladie("Dépendance aux réseaux sociaux", "DRS", 1, 5);
                    this.listeMaladies.add(m2);
                case(3):
                    Maladie m3 =new Maladie("Porphyrie érythropoïétique congénitale", "PEC", 1, 5);
                    this.listeMaladies.add(m3);
                case(4):
                    Maladie m4 =new Maladie("Zoopathie paraphénique lycanthropique", "ZPL", 1, 5);
                    this.listeMaladies.add(m4);
                case(5):
                    Maladie m5 =new Maladie("Bégaiement Gustitif", "BG", 1, 5);
                    this.listeMaladies.add(m5);
            }
        }
    }

    /*public void sEmporter(){

    }*/
}
