package TP3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceMedicalTest {

    private ServiceMedical service;
    private ArrayList<Creature> listeCreatures;
    private ArrayList<Maladie> listeMaladies;

    @BeforeEach
    public void setUp() {
        // Préparation des maladies
        listeMaladies = new ArrayList<>();
        listeMaladies.add(new Maladie("Grippe", "GRP", 2, 5, true));
        listeMaladies.add(new Maladie("Rage", "RGE", 3, 5, true));

        // Préparation des créatures
        listeCreatures = new ArrayList<>();
        listeCreatures.add(new Nain("Gimli", "Homme", 100.0, 120, 139, 5, listeMaladies));
        listeCreatures.add(new Orque("Thrall", "Mâle", 150.0, 190, 50, 6, listeMaladies));

        // Création du service médical
        service = new ServiceMedical("Clinique des Montagnes", 200, 5, listeCreatures, "faible");
    }

    @Test
    public void testGetters() {
        assertEquals("Clinique des Montagnes", service.getNom());
        assertEquals(200, service.getSuperficie());
        assertEquals(5, service.getNombreMaximumCreatures());
        assertEquals(2, service.getNombreCreaturesPresente());
        assertEquals("faible", service.getBudget());
        assertEquals(listeCreatures, service.getListeCreatures());
    }

    @Test
    public void testSetBudgetValide() {
        service.setBudget("insuffisant");
        assertEquals("insuffisant", service.getBudget());

        service.setBudget("médiocre");
        assertEquals("médiocre", service.getBudget());
    }

    @Test
    public void testSetBudgetInvalide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.setBudget("luxueux"));
        assertEquals("Erreur de création : budget invalide.", exception.getMessage());
    }

    @Test
    public void testAjouterCreature() {
        Creature elfe = new Elfe("Legolas", "Homme", 60.0, 180, 120, 8, listeMaladies);
        service.ajouterCreature(elfe);

        assertTrue(service.getListeCreatures().contains(elfe));
        assertEquals(3, service.getNombreCreaturesPresente());
    }

    @Test
    public void testAjouterCreatureServicePlein() {
        for (int i = 0; i < 3; i++) {
            service.ajouterCreature(new Nain("Nain" + i, "Homme", 80.0, 150, 50, 5, listeMaladies));
        }

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            service.ajouterCreature(new Nain("Trop", "Homme", 90.0, 160, 55, 6, listeMaladies));
        });
        assertEquals("Le service médical est plein.", exception.getMessage());
    }

    @Test
    public void testRetirerCreature() {
        Creature creature = listeCreatures.get(0);
        service.retirerCreature(creature);

        assertFalse(service.getListeCreatures().contains(creature));
        assertEquals(1, service.getNombreCreaturesPresente());
    }

    @Test
    public void testRetirerCreatureInexistante() {
        Creature fantome = new Elfe("Fantôme", "Homme", 70.0, 180, 300, 7, listeMaladies);

        service.retirerCreature(fantome);

        // Vérifie qu'aucune erreur n'est levée et que rien n'est supprimé
        assertEquals(2, service.getNombreCreaturesPresente());
    }

    @Test
    public void testSoignerCreatures() {
        int niveauAvant = listeMaladies.get(0).getNiveauActuel();
        service.soignerCreatures();
        int niveauApres = listeMaladies.get(0).getNiveauActuel();

        assertTrue(niveauApres == niveauAvant || niveauApres == niveauAvant - 1,
                "Le niveau de la maladie doit diminuer de 1 ou rester identique.");
    }

    @Test
    public void testAfficherCaracteristiques() {
        service.afficherCaracteristiques();
        // Ici, on vérifie principalement que la méthode ne génère pas d'erreurs
    }
}
