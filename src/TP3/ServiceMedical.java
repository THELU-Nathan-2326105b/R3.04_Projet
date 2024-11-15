package TP3;

import java.util.ArrayList;

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
}
