package TP3;

import java.util.ArrayList;
import java.util.Random;

public class SimulationHopital {
    public static void main(String[] args) {
        // Création de l'hôpital
        Hopital hopital = new Hopital("Hôpital Fantastique", 3);

        // Création des services médicaux
        ServiceMedical triage = new ServiceMedical("Triage", 500, 5, new ArrayList<>(), "faible");
        Crypte crypte = new Crypte("Crypte des Régénérants", 300, 3, new ArrayList<>(), "inexistant", 5, 22);
        CentreQuarantaine quarantaine = new CentreQuarantaine("Centre de Quarantaine", 400, 4, new ArrayList<>(), "médiocre",true);

        // Ajout des services à l'hôpital
        hopital.ajouterService(triage);
        hopital.ajouterService(crypte);
        hopital.ajouterService(quarantaine);

        // Création des créatures
        ArrayList<Maladie> maladiesElfe = new ArrayList<>();
        maladiesElfe.add(new Maladie("Grippe", "GRP", 2, 5, true));
        Elfe elfe = new Elfe("Legolas", "Homme", 70, 180, 120, 5, maladiesElfe);

        ArrayList<Maladie> maladiesOrque = new ArrayList<>();
        maladiesOrque.add(new Maladie("MDC", "Maladie Débilitante Chronique", 4, 5, false));
        Orque orque = new Orque("Gorg", "Homme", 150, 200, 40, 3, maladiesOrque);

        ArrayList<Maladie> maladiesZombie = new ArrayList<>();
        maladiesZombie.add(new Maladie("ZPL", "Zoopathie Paraphénique Lycanthropique", 3, 5, true));
        Zombie zombie = new Zombie("Zorg", "Homme", 120, 160, 0, 2, maladiesZombie);

        // Ajout des créatures aux services
        triage.ajouterCreature(elfe);
        triage.ajouterCreature(orque);
        crypte.ajouterCreature(zombie);

        // Création des médecins
        Medecin medecin1 = new Medecin("Dr Strange", "Homme", 50);
        Medecin medecin2 = new Medecin("Dr House", "Homme", 60);

        // Ajout des médecins à l'hôpital
        hopital.ajouterMedecin(medecin1);
        hopital.ajouterMedecin(medecin2);

        MenuHopital menu = new MenuHopital(hopital);

        // Début de la simulation
        System.out.println("=== Début de la Simulation ===");
        for (int jour = 1; jour <= 5; jour++) {
            System.out.println("\n--- Jour " + jour + " ---");
            menu.afficherMenu();
            hopital.simuler();
        }

        // Affichage des résultats
        System.out.println("\n=== Résultat Final ===");
        hopital.afficherCreatures();
    }
}
