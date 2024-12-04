package TP3;

import java.util.Scanner;

public class MenuHopital {
    private Hopital hopital;

    public MenuHopital(Hopital hopital) {
        this.hopital = hopital;
    }

    public void afficherMenu() {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n=== Menu de gestion de l'hôpital ===");
            System.out.println("1. Afficher les services et leurs créatures");
            System.out.println("2. Afficher la liste des médecins");
            System.out.println("3. Affecter un médecin à un service médical");
            System.out.println("4. Faire soigner un service par un médecin");
            System.out.println("5. Passer au Jour suivant");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la ligne restante

            switch (choix) {
                case 1 -> afficherServices();
                case 2 -> afficherMedecins();
                case 3 -> affecterMedecin(scanner);
                case 4 -> soignerService(scanner);
                case 5 -> {
                    passerAuJourSuivant(hopital);
                    return;
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (true);  // Continuera jusqu'à ce qu'on choisisse l'option 5
    }

    public void afficherServices() {
        System.out.println("=== Liste des services médicaux ===");
        for (ServiceMedical service : hopital.getServices()) {
            System.out.println(service);
            System.out.println("  Créatures : ");
            for (Creature creature : service.getListeCreatures()) {
                System.out.println("    - " + creature);
            }
            System.out.println("--------------------------------------------------");  // Ligne de séparation
        }
    }

    public void afficherMedecins() {
        System.out.println("\n=== Liste des médecins ===");
        for (Medecin medecin : hopital.getMedecins()) {
            System.out.println(medecin);
        }
    }

    public void affecterMedecin(Scanner scanner) {
        afficherMedecins();
        System.out.print("\nEntrez l'index du médecin à affecter (0, 1, 2, ...): ");
        int indexMedecin = scanner.nextInt();
        scanner.nextLine();

        if (indexMedecin < 0 || indexMedecin >= hopital.getMedecins().size()) {
            System.out.println("Médecin invalide !");
            return;
        }

        Medecin medecin = hopital.getMedecins().get(indexMedecin);

        afficherServices();
        System.out.print("Entrez l'index du service à affecter (0, 1, 2, ...): ");
        int indexService = scanner.nextInt();
        scanner.nextLine();

        if (indexService < 0 || indexService >= hopital.getServices().size()) {
            System.out.println("Service invalide !");
            return;
        }

        ServiceMedical service = hopital.getServices().get(indexService);

        service.ajouterMedecin(medecin);
        System.out.println("Médecin " + medecin.getNom() + " affecté au service " + service.getNom() + ".");
    }

    public void soignerService(Scanner scanner) {
        afficherServices();
        System.out.print("\nEntrez l'index du service à soigner (0, 1, 2, ...): ");
        int indexService = scanner.nextInt();
        scanner.nextLine();

        if (indexService < 0 || indexService >= hopital.getServices().size()) {
            System.out.println("Service invalide !");
            return;
        }

        ServiceMedical service = hopital.getServices().get(indexService);

        afficherMedecins();
        System.out.print("Entrez l'index du médecin pour soigner (0, 1, 2, ...): ");
        int indexMedecin = scanner.nextInt();
        scanner.nextLine();

        if (indexMedecin < 0 || indexMedecin >= hopital.getMedecins().size()) {
            System.out.println("Médecin invalide !");
            return;
        }

        Medecin medecin = hopital.getMedecins().get(indexMedecin);

        medecin.soigner(service);
        System.out.println("Le médecin " + medecin.getNom() + " a soigné les créatures du service " + service.getNom() + ".");
    }

    public void passerAuJourSuivant(Hopital hopital) {
        hopital.simuler();
        System.out.println("Passage au jour suivant...");
    }
}
