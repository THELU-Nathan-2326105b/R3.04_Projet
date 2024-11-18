package TP3;

import java.util.ArrayList;

public class TestHopital {
    public static void main(String[] args) {
        System.out.println("Début des tests pour la simulation de l'hôpital.");

        // Création de l'hôpital
        Hopital hopital = new Hopital("Fantasy Hospital", 3);

        // Création de services médicaux
        ServiceMedical serviceOrques = new ServiceMedical("Service Orques", 150, 5, new ArrayList<>(), "faible");
        ServiceMedical serviceElfes = new ServiceMedical("Service Elfes", 100, 5, new ArrayList<>(), "médiocre");

        // Ajout des services médicaux à l'hôpital
        assert hopital.ajouterService(serviceOrques) : "Ajout du service des Orques échoué.";
        assert hopital.ajouterService(serviceElfes) : "Ajout du service des Elfes échoué.";

        // Création de créatures
        ArrayList<Maladie> maladiesOrques = new ArrayList<>();
        Orque orque1 = new Orque("Gorgutz", "Mâle", 150, 200, 30, 5, maladiesOrques);
        Orque orque2 = new Orque("Grimgor", "Mâle", 140, 195, 35, 4, maladiesOrques);

        ArrayList<Maladie> maladiesElfes = new ArrayList<>();
        Elfe elfe1 = new Elfe("Lindir", "Femelle", 65, 180, 120, 5, maladiesElfes);

        // Ajout des créatures dans les services
        serviceOrques.getListeCreatures().add(orque1);
        serviceOrques.getListeCreatures().add(orque2);
        serviceElfes.getListeCreatures().add(elfe1);

        // Vérification du nombre de créatures par service
        assert serviceOrques.getListeCreatures().size() == 2 : "Le service des Orques doit contenir 2 créatures.";
        assert serviceElfes.getListeCreatures().size() == 1 : "Le service des Elfes doit contenir 1 créature.";

        // Création et ajout de médecins
        Medecin medecin1 = new Medecin("Dr. Aenarion", "Mâle", 200);
        Medecin medecin2 = new Medecin("Dr. Thrall", "Mâle", 45);
        hopital.ajouterMedecin(medecin1);
        hopital.ajouterMedecin(medecin2);

        // Vérification des médecins ajoutés
        assert hopital != null : "L'hôpital ne doit pas être nul après création.";

        // Lancer une simulation
        System.out.println("\n--- Lancement de la simulation ---\n");
        hopital.simuler();

        // Vérifier après simulation que le service n'est pas vide
        assert !serviceOrques.getListeCreatures().isEmpty() : "Le service des Orques ne doit pas être vide après la simulation.";
        assert !serviceElfes.getListeCreatures().isEmpty() : "Le service des Elfes ne doit pas être vide après la simulation.";

        System.out.println("\nTests réussis. Fin de la simulation.");
    }
}
