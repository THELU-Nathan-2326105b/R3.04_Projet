package TP3;

import TP3.Lycanthropes.Lycanthrope;

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

    public void attendre(ServiceMedical serviceMedical) {
        if (this instanceof Orque || this instanceof HommeBete ||this instanceof Zombie || this instanceof Lycanthrope) {
            attendreEnTriage(serviceMedical);
        }
        else if(this instanceof Vampire || this instanceof Reptilien ||this instanceof Nain || this instanceof Elfe){
            attendreAvecEffetVIP(serviceMedical);
        }
        else{
            if (this.moralIndic <= 1) {
                this.hurler();
            } else {
                this.setMoralIndic(this.moralIndic - 1);
            }

        }
    }

    public void hurler(){
        System.out.print("Agrougrou");
    }

    // Liste des maladies en format String
    public ArrayList<String> getListeMaladies() {
        ArrayList<String> maladiesEnClair = new ArrayList<>();
        for (Maladie maladie : listeMaladies) {
            maladiesEnClair.add(maladie.toString());
        }
        return maladiesEnClair;
    }

    // Liste des maladies en format Maladie
    public ArrayList<Maladie> getListeMaladie() {
        return listeMaladies;
    }

    public void tomberMalade() {
        Random r1 = new Random();
        int nMal = r1.nextInt(6);
        switch (nMal) {
            case 0 -> listeMaladies.add(new Maladie("Maladie débilitante chronique", "MDC", 1, 5, false));
            case 1 -> listeMaladies.add(new Maladie("Syndrome fear of missing out", "FOMO", 1, 5, true));
            case 2 -> listeMaladies.add(new Maladie("Dépendance aux réseaux sociaux", "DRS", 1, 5, true));
            case 3 -> listeMaladies.add(new Maladie("Porphyrie érythropoïétique congénitale", "PEC", 1, 5, false));
            case 4 -> listeMaladies.add(new Maladie("Zoopathie paraphrénique lycanthropique", "ZPL", 1, 5, true));
            case 5 -> listeMaladies.add(new Maladie("Bégaiement Gustatif", "BG", 1, 5, true));
        }
        System.out.println(this.nomComplet + " a attrapé une nouvelle maladie.");
    }

    public void etreSoigne() {
        if (!listeMaladies.isEmpty()) {
            Maladie maladie = listeMaladies.getFirst();
            maladie.diminuerNiveau(1);  // Réduit le niveau de la maladie de 1
            if (maladie.getNiveauActuel() <= 1) {
                listeMaladies.remove(maladie);  // Si la maladie est guérie, on la retire de la liste
                System.out.println(this.nomComplet + " est guéri de : " + maladie.getNomComplet());
            }
        }
    }


    public boolean aMaladieContagieuse() {
        return listeMaladies.stream().anyMatch(Maladie::isContagieuse);
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

                if (this instanceof Zombie || this instanceof Vampire) {
                    regenerer(serviceMedical);
                }
                if (this instanceof HommeBete || this instanceof Lycanthrope || this instanceof Orque || this instanceof Vampire) {
                    serviceMedical.contaminer(this);
                }

                listeMaladies.clear();

                // Arrêter la méthode après la mort
                return;
            }
        }
    }

    public void sEmporter(ServiceMedical service) {
        System.out.println(this.nomComplet + " s'emporte !");
        Maladie maladieContagieuse = listeMaladies.stream()
                .filter(Maladie::isContagieuse)
                .findFirst()
                .orElse(null);

        if (maladieContagieuse != null) {
            Random random = new Random();
            ArrayList<Creature> creatures = service.getListeCreatures();
            Creature cible = creatures.get(random.nextInt(creatures.size()));

            if (!cible.equals(this)) {
                cible.tomberMaladeAvec(maladieContagieuse);
                System.out.println(cible.getNomComplet() + " a été contaminé par " + maladieContagieuse.getNomComplet());
            }
        }
    }

    public void attendreAvecEffetVIP(ServiceMedical service) {
            this.setMoralIndic(this.moralIndic - 2);
            System.out.println(this.nomComplet + " (VIP) perd 2 points de moral.");
    }

    public void attendreEnTriage(ServiceMedical service) {
            long nombreCongeneres = service.getListeCreatures().stream()
                    .filter(c -> c.getClass().equals(this.getClass()))
                    .count();
            if (nombreCongeneres > 1) {
                System.out.println(this.nomComplet + " tolère mieux l'attente grâce à la présence de congénères.");
            }
    }

    public void regenerer(ServiceMedical service) {
        System.out.println(this.nomComplet + " régénère après trépas !");
        this.setMoralIndic(5);
        service.ajouterCreature(this);
    }

    public void tomberMaladeAvec(Maladie maladie) {
        listeMaladies.add(maladie);
        System.out.println(this.nomComplet + " a attrapé : " + maladie.getNomComplet());
    }

    @Override
    public String toString() {
        return nomComplet + " (Age: " + age + ", Sexe: " + sexe + ")";
    }

}
