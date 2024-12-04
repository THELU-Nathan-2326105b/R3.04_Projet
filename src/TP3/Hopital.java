package TP3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe principale représentant l'hôpital fantastique.
 * L'hôpital gère des services médicaux, des médecins et des créatures.
 */
public class Hopital {
    private String nom;
    private int maxServices;
    private List<ServiceMedical> services;
    private List<Medecin> medecins;
    private int jour;  // Nouveau champ pour suivre le jour actuel

    /**
     * Constructeur pour initialiser un hôpital.
     * @param nom Nom de l'hôpital.
     * @param maxServices Nombre maximum de services médicaux.
     */
    public Hopital(String nom, int maxServices) {
        this.nom = nom;
        this.maxServices = maxServices;
        this.services = new ArrayList<>();
        this.medecins = new ArrayList<>();
        this.jour = 1;  // Initialiser le jour à 1 au départ
    }

    // Getter pour les médecins
    public ArrayList<Medecin> getMedecins() {
        return (ArrayList<Medecin>) medecins;
    }

    public ArrayList<ServiceMedical> getServices() {
        return (ArrayList<ServiceMedical>) services;
    }

    // Getter pour le jour
    public int getJour() {
        return jour;
    }

    // Incrémenter le jour (passage au jour suivant)
    public void incrementerJour() {
        jour++;  // Augmenter le jour de 1
    }

    /**
     * Ajoute un service médical à l'hôpital.
     * @param service Service médical à ajouter.
     * @return true si l'ajout est réussi, false sinon.
     */
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
                System.out.print(" - " + creature.getNomComplet());
                if (creature.getListeMaladie().isEmpty()){
                    System.out.println(" ne possède pas de maladie");
                }
                else if (creature.getListeMaladie().size() == 1){
                    System.out.println(" possède la maladie " + creature.getListeMaladie());
                }
                else {
                    System.out.println(" possède les maladies " + creature.getListeMaladie());
                }
            }
        }
    }

    /**
     * Lance la simulation d'une journée dans l'hôpital.
     */
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
                        creature.attendre(service);
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
                for(int i =0; i<creature.getListeMaladie().size(); i++){
                    Random random2 = new Random();
                    double valeur2 = random2.nextDouble(); // Génère une valeur aléatoire entre 0.0 et 1.0
                    if (valeur2 < 0.5) {
                        creature.getListeMaladie().get(i).augmenterNiveau(1);
                        System.out.println("Le niveau de la maladie " + creature.getListeMaladie().get(i) + " est passé à " + creature.getListeMaladie().get(i).getNiveauActuel() );
                    }
                    else{
                        creature.getListeMaladie().get(i).diminuerNiveau(1);
                        System.out.println("Le niveau de la maladie " + creature.getListeMaladie().get(i) + " est passé à " + creature.getListeMaladie().get(i).getNiveauActuel() );
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
            System.out.println(medecin.getNom() + " vérifie les services.");
            if (random.nextBoolean() && !services.isEmpty()) {
                ServiceMedical service = services.get(random.nextInt(services.size()));
                if (!service.getListeCreatures().isEmpty()) {
                    Creature creature = service.getListeCreatures().get(random.nextInt(service.getListeCreatures().size()));
                    System.out.println(medecin.getNom() + " soigne " + creature.getNomComplet() + " dans le service " + service.getNom() + ".");
                    medecin.soigner(service);
                }
            }
        }

        System.out.println("\nFin de la simulation de la journée.");
    }
}
