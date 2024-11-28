package TP3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CentreQuarantaineTest {

    @Test
    public void testCreationCentreQuarantaine() {
        // Préparer les maladies
        ArrayList<Maladie> maladies = new ArrayList<>();
        maladies.add(new Maladie("Grippe", "SYM1", 2, 5, true));
        maladies.add(new Maladie("Rhume", "SYM2", 1, 3, true));

        // Préparer les créatures
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(new Nain("Gimli", "Homme", 50, 100, 150, 10, maladies));

        // Créer un Centre de Quarantaine
        CentreQuarantaine centre = new CentreQuarantaine(
                "Centre Orcs",
                500,
                10,
                creatures,
                "faible",
                true
        );

        // Vérifications
        assertEquals("Centre Orcs", centre.getNom());
        assertEquals(500, centre.getSuperficie());
        assertEquals(10, centre.getNombreMaximumCreatures());
        assertEquals(1, centre.getNombreCreaturesPresente());
        assertEquals("faible", centre.getBudget());
    }

    @Test
    public void testAjoutCreatureValide() {
        // Préparer une maladie contagieuse
        ArrayList<Maladie> maladies = new ArrayList<>();
        maladies.add(new Maladie("Grippe", "SYM1", 2, 5, true));

        // Préparer une créature contagieuse
        Orque Orque = new Orque("Gorg", "Orc", 60, 120,10,1, maladies);

        // Créer un Centre de Quarantaine vide
        CentreQuarantaine centre = new CentreQuarantaine(
                "Centre Orcs",
                500,
                5,
                new ArrayList<>(),
                "faible",
                true
        );

        // Ajouter la créature contagieuse
        boolean ajoutReussi = centre.ajouterCreatureQ(Orque);

        // Vérifications
        assertTrue(ajoutReussi);
        assertEquals(1, centre.getNombreCreaturesPresente());
        assertEquals(Orque, centre.getListeCreatures().get(0));
    }

    @Test
    public void testAjoutCreatureNonContagieuse() {
        // Préparer une maladie non contagieuse
        ArrayList<Maladie> maladies = new ArrayList<>();
        maladies.add(new Maladie("Fracture", "SYM3", 0, 0, false));

        // Préparer une créature non contagieuse
        Nain nain = new Nain("Thorin", "Homme", 80, 110, 160, 15, maladies);

        // Créer un Centre de Quarantaine
        CentreQuarantaine centre = new CentreQuarantaine(
                "Centre des Hommes",
                500,
                5,
                new ArrayList<>(),
                "faible",
                true
        );

        // Essayer d'ajouter la créature non contagieuse
        boolean ajoutReussi = centre.ajouterCreatureQ(nain);

        // Vérifications
        assertFalse(ajoutReussi);
        assertEquals(0, centre.getNombreCreaturesPresente());
    }

    @Test
    public void testAjoutCreatureCentrePlein() {
        // Préparer une maladie contagieuse
        ArrayList<Maladie> maladies = new ArrayList<>();
        maladies.add(new Maladie("Grippe", "SYM1", 2, 5, true));

        // Préparer des créatures
        Orque orc1 = new Orque("Orc1", "Orc", 50, 120,10,1, maladies);
        Orque orc2 = new Orque("Orc2", "Orc", 60, 130,12,3, maladies);

        // Créer un Centre de Quarantaine avec capacité 1
        CentreQuarantaine centre = new CentreQuarantaine(
                "Petit Centre Orcs",
                300,
                1,
                new ArrayList<>(),
                "faible",
                true
        );

        // Ajouter la première créature
        centre.ajouterCreatureQ(orc1);

        // Essayer d'ajouter une deuxième créature
        boolean ajoutReussi = centre.ajouterCreatureQ(orc2);

        // Vérifications
        assertFalse(ajoutReussi);
        assertEquals(1, centre.getNombreCreaturesPresente());
    }

    @Test
    public void testRevisionBudgetAvecIsolation() {
        // Créer un Centre de Quarantaine avec isolation
        CentreQuarantaine centre = new CentreQuarantaine(
                "Centre Zombie",
                400,
                5,
                new ArrayList<>(),
                "faible",
                true
        );

        // Réviser le budget
        centre.setBudget("moyen");

        // Vérifications
        assertEquals("moyen", centre.getBudget());
        assertTrue(centre.isIsolation());
    }
}
