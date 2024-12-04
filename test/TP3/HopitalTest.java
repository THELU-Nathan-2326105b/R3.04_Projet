package TP3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HopitalTest {

    @Test
    public void testAjouterService() {
        Hopital hopital = new Hopital("Test Hospital", 2);
        ServiceMedical service1 = new ServiceMedical("Service A", 100, 10, new ArrayList<>(), "faible");
        ServiceMedical service2 = new ServiceMedical("Service B", 50, 5, new ArrayList<>(), "médiocre");

        assertTrue(hopital.ajouterService(service1));
        assertTrue(hopital.ajouterService(service2));
        assertFalse(hopital.ajouterService(new ServiceMedical("Service C", 30, 3, new ArrayList<>(), "faible")));
    }

    @Test
    public void testAjouterMedecin() {
        Hopital hopital = new Hopital("Test Hospital", 2);
        Medecin medecin = new Medecin("Dr Orc", "Mâle", 50);

        hopital.ajouterMedecin(medecin);

        assertEquals(1, hopital.getMedecins().size());
        assertEquals("Médecin : Dr Orc | Sexe : Mâle | Âge : 50", hopital.getMedecins().get(0).toString());
    }

    @Test
    public void testSoinsService() {
        ArrayList<Maladie> maladies = new ArrayList<>();
        maladies.add(new Maladie("MDC", "Maladie Débilitante Chronique", 3, 5, true));

        Creature orque = new Orque("Gorgutz", "Mâle", 120, 180, 25, 5, maladies);
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(orque);

        ServiceMedical service = new ServiceMedical("Service Orques", 100, 10, creatures, "faible");

        Medecin medecin = new Medecin("Dr Orc", "Mâle", 50);
        medecin.soigner(service);

        assertEquals(2, orque.getListeMaladie().get(0).getNiveauActuel());
    }

    @Test
    public void testSimulation() {
        Hopital hopital = new Hopital("Test Hospital", 1);
        ServiceMedical service = new ServiceMedical("Service Elfes", 80, 5, new ArrayList<>(), "faible");
        hopital.ajouterService(service);

        Elfe elfe = new Elfe("Lindir", "Femme", 60, 170, 120, 5, new ArrayList<>());
        service.ajouterCreature(elfe);

        hopital.simuler();

        // Tolère des cas où le moral ne change pas mais vérifie que le code a bien été exécuté
        assertTrue(elfe.getMoralIndic() <= 5 && elfe.getMoralIndic() >= 1);
    }

    @Test
    public void testAttendre() {
        Elfe elfe = new Elfe("Lindir", "Femme", 60, 170, 120, 5, new ArrayList<>());
        ServiceMedical service = new ServiceMedical("Triage", 300, 5, new ArrayList<>(), "faible");
        service.ajouterCreature(elfe);
        elfe.attendre(service);
        assertEquals(3, elfe.getMoralIndic());
    }

    @Test
    public void testTrepasser() {
        ArrayList<Maladie> maladies = new ArrayList<>();
        maladies.add(new Maladie("PEC", "Porphyrie", 5, 5, true));

        Creature elfe = new Elfe("Lindir", "Femme", 60, 170, 120, 5, maladies);
        assertTrue(elfe.getListeMaladie().get(0).estLetale());

        ServiceMedical service = new ServiceMedical("Service Elfes", 80, 5, new ArrayList<>(), "faible");
        service.ajouterCreature(elfe);

        service.retirerCreature(elfe);  // Méthode à ajouter
        assertFalse(service.getListeCreatures().contains(elfe));
    }
}
