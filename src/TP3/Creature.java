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

    public Creature(String nomComplet, String sexe, double poids, int taille, int moralIndic, ArrayList<Maladie> listeMaladies) {
        this.nomComplet = nomComplet;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moralIndic = moralIndic;
        this.listeMaladies = listeMaladies;
    }

    public double getPoids() {
        return poids;
    }

    public String getSexe() {
        return sexe;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setMoralIndic(int moralIndic) {
        this.moralIndic = moralIndic;
    }

    public int getMoralIndic() {
        return moralIndic;
    }

    public String getNom() {
        return nomComplet;
    }

    public void attendre() {
        if (this.moralIndic <= 1) {
            this.hurler();
        } else {
            this.setMoralIndic(this.moralIndic - 1);
        }
    }

    public void hurler() {
        System.out.print("Agrougrou");
    }

    //En format String
    public ArrayList<String> getListeMaladies() {
        ArrayList<String> maladiesEnClair = new ArrayList<>();
        for (Maladie maladie : listeMaladies) {
            maladiesEnClair.add(maladie.toString()); // Utilise la méthode toString() de Maladie
        }
        return maladiesEnClair;
    }

    //En format Maladie
    public ArrayList<Maladie> getListeMaladie() {
        return listeMaladies;
    }

    public void tomberMalade() {
        Random r1 = new Random();
        int nb = r1.nextInt(6);
        if (nb != 0) {
            int nMal = r1.nextInt(6);
            System.out.println(nMal);
            if (nMal == 0) {
                Maladie m0 = new Maladie("Maladie débilitante chronique", "MDC", 1, 5, false);
                this.listeMaladies.add(m0);
            }
            if (nMal == 1) {
                Maladie m1 = new Maladie("Syndrome fear of missing out", "FOMO", 1, 5, true);
                this.listeMaladies.add(m1);
            }
            if (nMal == 2) {
                Maladie m2 = new Maladie("Dépendance aux réseaux sociaux", "DRS", 1, 5, true);
                this.listeMaladies.add(m2);
            }
            if (nMal == 3) {
                Maladie m3 = new Maladie("Porphyrie érythropoïétique congénitale", "PEC", 1, 5, false);
                this.listeMaladies.add(m3);
            }
            if (nMal == 4) {
                Maladie m4 = new Maladie("Zoopathie paraphénique lycanthropique", "ZPL", 1, 5, true);
                this.listeMaladies.add(m4);
            }
            if (nMal == 5) {
                Maladie m5 = new Maladie("Bégaiement Gustatif", "BG", 1, 5, true);
                this.listeMaladies.add(m5);
            }
        }
    }

    public void etreSoigne() {
        Random r1 = new Random();
        int nb = r1.nextInt(6);
        if (nb == 0) {
            int nbMal = r1.nextInt(this.listeMaladies.size());
            this.listeMaladies.remove(nbMal);
        }
    }

    public boolean aMaladieContagieuse() {
        for (Maladie maladie : listeMaladies) {
            if (maladie.isContagieuse()) {
                return true;
            }
        }
        return false;
    }

    public void trepasser(ServiceMedical serviceMedical) {
        // Vérifie si la créature a une maladie létale
        for (Maladie maladie : listeMaladies) {
            if (maladie.estLetale()) {
                // Affiche un message indiquant que la créature est morte
                System.out.println("La créature " + this.getNom() + " est morte à cause de la maladie : " + maladie.getNomComplet());

                // Supprime la créature du service médical
                serviceMedical.retirerCreature(this);

                // Si la créature est un Elfe ou un Vampire, elle démoralise les autres membres
                if (this instanceof Elfe || this instanceof Vampire) {
                    System.out.println("La mort de " + this.getNom() + " démoralise les autres créatures.");
                    serviceMedical.reduireMoralDesAutres(this);
                }

                // Vider la liste des maladies (facultatif)
                listeMaladies.clear();

                // Arrêter la méthode après la mort
                return;
            }
        }
    }


    /*public void sEmporter(){

    }*/
}