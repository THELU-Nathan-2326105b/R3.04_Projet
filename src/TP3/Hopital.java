package TP3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hopital {
    private String nom;
    private int maxServices;
    private List<ServiceMedical> services;
    private List<Medecin> medecins;

    public Hopital(String nom, int maxServices) {
        this.nom = nom;
        this.maxServices = maxServices;
        this.services = new ArrayList<>();
        this.medecins = new ArrayList<>();
    }
    // Getter pour les médecins
    public ArrayList<Medecin> getMedecins() {
        return (ArrayList<Medecin>) medecins;
    }


    // Ajouter un service médical
    public boolean ajouterService(ServiceMedical service) {
        if (services.size() < maxServices) {
            services.add(service);
            return true;
        }
        return false;
    }

    // Ajouter un médecin
    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
    }

    // Afficher les créatures de tous les services
    public void afficherCreatures() {
        System.out.println("Créatures dans tous les services :");
        for (ServiceMedical service : services) {
            System.out.println("Service : " + service.getNom());
            for (Creature creature : service.getListeCreatures()) {
                System.out.println(" - ");
            }
        }
    }

    public void simuler() {
        System.out.println("\n--- Simulation d'une journée dans l'hôpital : " + nom + " ---");

        Random random = new Random();

        for (ServiceMedical service : services) {
            System.out.println("\nService : " + service.getNom());

            // Actions sur chaque créature
            ArrayList<Creature> creatures = new ArrayList<>(service.getListeCreatures());
            for (Creature creature : creatures) {
                int action = random.nextInt(3);

                switch (action) {
                    case 0 -> { // Attente
                        System.out.println(creature.getNomComplet() + " attend...");
                        creature.attendre();
                    }
                    case 1 -> { // Tomber malade
                        System.out.println(creature.getNomComplet() + " risque de tomber malade.");
                        creature.tomberMalade();
                    }
                    case 2 -> { // Soins aléatoires
                        System.out.println("Un médecin soigne " + creature.getNomComplet() + ".");
                        creature.etreSoigne();
                    }
                }

                // Vérification du trépas
                creature.trepasser(service);
            }

            // Ajustement aléatoire du budget du service
            int budgetChange = random.nextInt(3) - 1; // -1, 0 ou 1
            String[] niveauxBudget = {"faible", "moyen", "élevé"};
            int budgetActuel = java.util.Arrays.asList(niveauxBudget).indexOf(service.getBudget());
            int nouveauBudget = Math.max(0, Math.min(budgetActuel + budgetChange, niveauxBudget.length - 1));
            service.setBudget(niveauxBudget[nouveauBudget]);
            System.out.println("Le budget du service est maintenant : " + niveauxBudget[nouveauBudget]);
        }

        // Actions aléatoires sur les médecins
        for (Medecin medecin : medecins) {
            System.out.println(getMedecins() + " vérifie les services.");
            if (random.nextBoolean() && !services.isEmpty()) {
                ServiceMedical service = services.get(random.nextInt(services.size()));
                if (!service.getListeCreatures().isEmpty()) {
                    Creature creature = service.getListeCreatures().get(random.nextInt(service.getListeCreatures().size()));
                    System.out.println(getMedecins() + " soigne " + creature.getNomComplet() + " dans le service " + service.getNom() + ".");
                    medecin.soigner(service);
                }
            }
        }

        System.out.println("\nFin de la simulation de la journée.");
    }

}
