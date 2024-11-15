package TP3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ServiceMedicalTest {

    @Test
    public void testCreationServiceMedical() {
        // Préparer les données
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5);
        Maladie corida = new Maladie("Corida", "cda", 1, 5);
        listeMal.add(malaria);
        listeMal.add(corida);

        Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 5, listeMal);
        ArrayList<Creature> listeCreatures = new ArrayList<>();
        listeCreatures.add(n1);

        // Créer un service médical valide
        ServiceMedical service = new ServiceMedical("Clinique Elfique", 200, 5, listeCreatures, "inexistant");

        // Vérifications
        assertEquals("Clinique Elfique", service.getNom());
        assertEquals(200, service.getSuperficie());
        assertEquals(5, service.getNombreMaximumCreatures());
        assertEquals(1, service.getNombreCreaturesPresente());
        assertEquals(listeCreatures, service.getListeCreatures());
        assertEquals("inexistant", service.getBudget());
    }

    @Test
    public void testCreationServiceMedicalBudgetInvalide() {
        // Préparer les données
        ArrayList<Creature> listeCreatures = new ArrayList<>();

        // Vérifier qu'une exception est levée pour un budget invalide
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ServiceMedical("Clinique Elfique", 200, 5, listeCreatures, "luxueux");
        });
        assertEquals("Erreur de création : budget invalide.", exception.getMessage());
    }

    @Test
    public void testCreationServiceMedicalBudgetNull() {
        // Préparer les données
        ArrayList<Creature> listeCreatures = new ArrayList<>();

        // Vérifier qu'une exception est levée pour un budget null
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ServiceMedical("Clinique Elfique", 200, 5, listeCreatures, null);
        });
        assertEquals("Erreur de création : budget invalide.", exception.getMessage());
    }
}
