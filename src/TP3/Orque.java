package TP3;

public class Orque {
    private String nomComplet; //Nom puis prénom
    private String sexe; //Tout choix possible
    private double poids; //En kilogramme
    private int taille; //En centimètre
    private int age;
    private int moralIndic; //Oscille entre 5 et 1
    //private ArrayList<Maladie> listeMaladies;

    public Orque(String nomComplet, String sexe, double poids, int taille, int age, int moralIndic/*, ArrayList<Maladie> listeMaladies*/) {
        this.nomComplet = nomComplet;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moralIndic = moralIndic;
        //this.listeMaladies = listeMaladies;
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
        System.out.print("TRUP GURM-IRZI RIPÛRZ GOZADÛRZ !!!"); // Traduction Orc-Français : Corps dénoyauté par des pantalons en peau clouté
    }
}