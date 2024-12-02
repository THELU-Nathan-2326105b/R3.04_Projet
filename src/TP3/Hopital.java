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

    // Simuler des événements temporels
    public void simuler() {
        Random random = new Random();
        System.out.println("Début de la simulation pour l'hôpital : " + nom);

        // Modifier l'état des créatures
        for (ServiceMedical service : services) {
            System.out.println("\nService : " + service.getNom());
            for (Creature creature : service.getListeCreatures()) {
                // Action aléatoire
                int event = random.nextInt(3); // 0: attendre, 1: tomber malade, 2: s'emporter
                switch (event) {
                    case 0 -> creature.attendre();
                    case 1 -> creature.tomberMalade();
                    //case 2 -> creature.sEmporter();
                }

//                // Vérifier si la créature décède
//                if (creature.trepasser()) {
//                    System.out.println(creature.getNomComplet() + " a trépassé.");
//                    service.getListeCreatures().remove(creature);
//                    continue;
//                }

                // Afficher les maladies de la créature
                System.out.println("Maladies " + " :");
                if (creature.getListeMaladies().isEmpty()) {
                    System.out.println("  Aucune maladie.");
                } else {
                    for (Maladie maladie : creature.getListeMaladie()) {
                        System.out.println("  - " + maladie.getNomComplet() + " (Niveau : " + maladie.getNiveauActuel() + "/" + maladie.getNiveauMaximum() + ")");
                    }
                }
            }
        }

        // Modifier les services médicaux
        for (ServiceMedical service : services) {
            if (random.nextBoolean()) {
                System.out.println("Révision du budget pour le service : " + service.getNom());
                // Simuler une révision aléatoire du budget
            }
        }

        // Actions des médecins
        for (Medecin medecin : medecins) {
            if (!services.isEmpty()) {
                ServiceMedical service = services.get(random.nextInt(services.size()));
                medecin.soigner(service);
            }
        }

        System.out.println("Fin de la simulation.");
    }
}
