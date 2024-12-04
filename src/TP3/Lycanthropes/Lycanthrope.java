package TP3.Lycanthropes;

import TP3.Creature;
import TP3.Maladie;

import java.util.ArrayList;
import java.util.Objects;

public class Lycanthrope extends Creature {
    String age; //Catégorie d'âge au choix entre "jeune", "adulte" et "vieux"
    int force; //Force du lycanthrope sans limite
    int facteurDom; // Différence entre rangDom exercées et rangDom subies
    int rangDom; // Le rang de domination du lycanthrope
    int  niveau; // Critère de qualité subjectif du lycanthrope qui se calcule en fonction de différents critères
    int impetuosite; // Facteur d'impetuosite
    String meute; // Meute de loup ou solitaire s'il n'a pas de meute

    public Lycanthrope(String nomComplet, String age, String sexe, double poids, int taille, int moralIndic, ArrayList<Maladie> listeMaladies, int force, int facteurDom, int rangDom, int niveau, int impetuosite, String meute) {
        super(nomComplet, sexe, poids, taille, moralIndic, listeMaladies);
        this.age = age;
        this.force = force;
        this.facteurDom = facteurDom;
        this.rangDom = rangDom;
        this.niveau = niveau;
        this.impetuosite = impetuosite;
        this.meute = meute;
    }

    public String getMeute() {
        return meute;
    }

    public int getFacteurDom() {
        return facteurDom;
    }

    public int getRangDom() {
        return rangDom;
    }

    public void afficheCaracteristique() {
        System.out.println("NomComplet : " + this.getNomComplet() + " Age : "+ this.age+ " Sexe : " + this.getSexe() +
                " Poids : " + this.getPoids() + " Taille : " + this.getTaille() + " MoralIndic : " + this.getMoralIndic() +
                " ListeMal" + this.getListeMaladies() + " Force : " + this.force + " FacteurDom : " + this.facteurDom +
                " RangDom : " + this.rangDom + " Niveau : " + this.niveau + " Impetuosite : " + this.impetuosite + " Meute : " + this.meute);
    }

    public void hurler(){
        if (Objects.equals(this.age, "vieux")){
            System.out.print("AAAAAAAAAAHHHHHHHHHH");
        }
        else if (Objects.equals(this.age, "jeune")){
            System.out.print("KIKIKI");
        }
        else if (Objects.equals(this.age, "adulte")){
            System.out.print("JE DOIS ENCORE PAYER DES IMPÔTS ???!!!");
        }
    }
}
