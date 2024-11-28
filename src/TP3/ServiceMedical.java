package TP3;

import java.util.ArrayList;
import java.util.Random;

public class ServiceMedical {
    private String nom;
    private int superficie;
    private int nombreMaximumCreatures;
    private ArrayList<Creature> listeCreatures;
    private String budget;

    public ServiceMedical(String nom, int superficie, int nombreMaximumCreatures, ArrayList<Creature> listeCreatures, String budget) {
        if (budget == null || (!budget.equals("inexistant") && !budget.equals("médiocre") && !budget.equals("insuffisant") && !budget.equals("faible"))) {
            throw new IllegalArgumentException("Erreur de création : budget invalide.");
        }
        this.nom = nom;
        this.superficie = superficie;
        this.nombreMaximumCreatures = nombreMaximumCreatures;
        this.listeCreatures = new ArrayList<>(listeCreatures); // Copie défensive pour éviter des modifications extérieures
        this.budget = budget;
    }

    public String getNom() {
        return nom;
    }

    public int getSuperficie() {
        return superficie;
    }

    public int getNombreMaximumCreatures() {
        return nombreMaximumCreatures;
    }

    public int getNombreCreaturesPresente() {
        return listeCreatures.size();
    }

    public ArrayList<Creature> getListeCreatures() {
        return listeCreatures;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        if (budget == null ||
                (!budget.equals("inexistant") &&
                        !budget.equals("médiocre") &&
                        !budget.equals("insuffisant") &&
                        !budget.equals("faible") &&
                        !budget.equals("moyen") &&
                        !budget.equals("élevé"))) {
            throw new IllegalArgumentException("Erreur de création : budget invalide.");
        }
        this.budget = budget;
    }

    public void afficherCaracteristiques() {
        System.out.println("Nom : " + nom);
        System.out.println("Superficie : " + superficie);
        System.out.println("Nombre maximum de créatures : " + nombreMaximumCreatures);
        System.out.println("Nombre de créatures présentes : " + getNombreCreaturesPresente());
        System.out.println("Budget : " + budget);
        System.out.println("Liste des créatures :");
        for (Creature creature : listeCreatures) {
            System.out.println(" - " + creature);
        }
    }

    public void ajouterCreature(Creature creature) {
        if (listeCreatures.size() >= nombreMaximumCreatures) {
            throw new IllegalStateException("Le service médical est plein.");
        }
        if (!estTypeCompatible(creature)) {
            throw new IllegalArgumentException("Type de créature incompatible avec ce service médical.");
        }
        listeCreatures.add(creature);
    }

    // Ajout de la méthode pour retirer une créature
    public void retirerCreature(Creature creature) {
        if (listeCreatures.contains(creature)) {
            listeCreatures.remove(creature);
            System.out.println(creature.getNom() + " a été retirée du service " + this.nom + ".");
        } else {
            System.out.println(creature.getNom() + " n'est pas présent dans ce service.");
        }
    }


    public void soignerCreatures() {
        Random r1 = new Random();

        for (Creature creature : listeCreatures) {
            for (Maladie maladie : creature.getListeMaladie()) {
                int nb = r1.nextInt(5);
                if (nb == 0) {
                    int niveauActuel = maladie.getNiveauActuel();
                    if (niveauActuel > 0) { // Diminue le niveau uniquement s'il est supérieur à 0
                        maladie.setNiveauActuel(niveauActuel - 1);
                        System.out.println("La maladie " + maladie.getNomComplet() + " de " + creature.getNom() + " a été soignée, nouveau niveau : " + maladie.getNiveauActuel());
                    }
                }
            }
        }
    }

    protected boolean estTypeCompatible(Creature creature) {
        return true;
    }
}