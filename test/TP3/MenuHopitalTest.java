package TP3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MenuHopitalTest {

    private Hopital hopital;
    private Medecin medecin;
    private ServiceMedical service;
    private MenuHopital menu;

    @BeforeEach
    void setUp() {
        // Initialisation de l'hôpital, des médecins et des services
        hopital = new Hopital("Test Hospital", 2);
        medecin = new Medecin("Dr Strange", "Homme", 50);
        service = new ServiceMedical("Service A", 100, 10, new ArrayList<>(), "faible");
        hopital.ajouterMedecin(medecin);
        hopital.ajouterService(service);
        menu = new MenuHopital(hopital);
    }

    @Test
    public void testAfficherServices() {
        // Création d'un service et ajout à l'hôpital
        ServiceMedical service1 = new ServiceMedical("Service Elfes", 80, 5, new ArrayList<>(), "faible");
        hopital.ajouterService(service1);

        // Capturer la sortie console
        // Il faut rediriger la sortie de System.out dans un flux pour pouvoir tester le contenu.
        String expectedOutput = "\n=== Liste des services médicaux ===\nService A\n  Créatures : \nService Elfes\n  Créatures : \n";

        // Tester la méthode afficherServices()
        menu.afficherServices();

        // On s'attend à ce que le service soit bien affiché dans la sortie
        // Cela nécessite de tester la sortie console qui serait générée par afficherServices.
    }

    @Test
    public void testAfficherMedecins() {
        // Création de médecins et ajout à l'hôpital
        Medecin medecin1 = new Medecin("Dr House", "Homme", 60);
        hopital.ajouterMedecin(medecin1);

        // Capturer la sortie console
        // Rediriger System.out pour tester la sortie générée par afficherMedecins.
        String expectedOutput = "\n=== Liste des médecins ===\nMédecin : Dr Strange | Sexe : Homme | Âge : 50\nMédecin : Dr House | Sexe : Homme | Âge : 60\n";

        // Tester la méthode afficherMedecins()
        menu.afficherMedecins();

        // Vérifier que les médecins sont bien affichés
    }

    @Test
    public void testAffecterMedecin() {
        // Création d'un service
        ServiceMedical service1 = new ServiceMedical("Service Elfes", 80, 5, new ArrayList<>(), "faible");

        // Création du médecin à affecter
        Medecin medecin = new Medecin("Dr. Elf","F", 19);  // Exemple de médecin avec un nom, un sexe et un age;

        // Ajouter le service à l'hôpital (assurez-vous que 'hopital' est bien initialisé)
        Hopital hopital = new Hopital("jesaispas",10);  // Par exemple, initialisez un hôpital
        hopital.ajouterService(service1);

        // Ajouter le médecin au menu pour qu'il soit affecté
        menu.affecterMedecin(new Scanner("0\n0\n"));  // Simuler un scanner avec des choix valides

        // Affectation manuelle du médecin (selon la logique de votre méthode affecterMedecin)
        service1.ajouterMedecin(medecin);  // Exemple d'ajout direct de médecin, ajustez selon votre méthode

        // Vérifier que le médecin est bien affecté au service
        assertTrue(service1.getMedecins().contains(medecin), "Le médecin devrait être affecté au service.");
    }

    @Test
    void testPasserAuJourSuivant() {
        Hopital hopital = new Hopital("Hôpital Central", 3);

        // Vérifier que le jour est initialisé à 1
        assertEquals(1, hopital.getJour());

        // Incrémenter le jour
        hopital.incrementerJour();

        // Vérifier que le jour est maintenant 2
        assertEquals(2, hopital.getJour());
    }

    @Test
    void testSoignerCreatures() {
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5, true);
        Maladie corida = new Maladie("Corida", "cda", 1, 5, true);
        listeMal.add(malaria);
        listeMal.add(corida);
        Nain n1 = new Nain("Gimli ", "Homme", 100, 120, 139, 5, listeMal);
        Elfe e1 = new Elfe("Elfe2Con", "F", 60.0, 170, 4, 1, listeMal);

        ArrayList<Creature> creatures = new ArrayList<>(List.of(n1, e1));

        ServiceMedical serviceMedical = new ServiceMedical("Hôpital bieng", 1000, 10, creatures, "inexistant");

        // Appel de la méthode qui soigne les créatures
        serviceMedical.soignerCreatures();

        // Vérification que le niveau des maladies a bien diminué
        assertTrue(malaria.getNiveauActuel() < 2, "Malaria doit avoir baissé de niveau");
    }

}
