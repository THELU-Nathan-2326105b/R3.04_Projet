package TP3;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CreatureTest {
    @Test
    public void testAttendre() {
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5, true);
        Maladie corida = new Maladie("Corida", "cda", 1, 5, true);
        listeMal.add(malaria);
        listeMal.add(corida);
        Nain n1 = new Nain("Gimli ", "Homme", 100, 120, 139, 5, listeMal);
        n1.attendre();
        int moral2 = n1.getMoralIndic();
        assertEquals(4, moral2);
    }

    @Test
    public void testHurler() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Nain n1 = new Nain("Gimli ", "Homme", 100, 120, 139, 1, listeMal);
        try {

            n1.hurler();
            String expectedOutput = "UNE MINE !!!";
            assertEquals(expectedOutput, outputStream.toString().trim());
        } finally {
            System.setOut(originalOut);

        }
        n1.hurler();
    }

    @Test
    public void testTomberMalade() {
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5, true);
        Maladie corida = new Maladie("Corida", "cda", 1, 5, true);
        listeMal.add(malaria);
        listeMal.add(corida);
        Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 3, listeMal);
        n1.tomberMalade();
        assertTrue(n1.getListeMaladie().size() > 2);
    }

    @Test
    public void testEtreSoigne() {
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5, true);
        Maladie corida = new Maladie("Corida", "cda", 1, 5, true);
        listeMal.add(malaria);
        listeMal.add(corida);
        Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 3, listeMal);
        n1.etreSoigne();
        assertTrue(n1.getListeMaladie().size() < 2);
    }

    @Test
    public void testTrepassementAvecDemoralisation() {
        ArrayList<Maladie> maladiesLethales = new ArrayList<>();
        Maladie maladieLetale = new Maladie("Grippe", "GRP", 5, 5, true);
        maladiesLethales.add(maladieLetale);

        Creature elfe = new Elfe("Legolas", "Homme", 100, 180, 120, 3, maladiesLethales);
        ServiceMedical serviceMedical = new ServiceMedical("Service Fantastique", 300, 5, new ArrayList<>(), "faible");
        serviceMedical.ajouterCreature(elfe);

        elfe.trepasser(serviceMedical);
        assertFalse(serviceMedical.getListeCreatures().contains(elfe));
    }

    @Test
    public void testSEmporter() {
        ArrayList<Maladie> listeMalElfe = new ArrayList<>();
        Maladie maladieContagieuse = new Maladie("Maladie Contagieuse", "MC", 2, 5, true);
        listeMalElfe.add(maladieContagieuse);

        ArrayList<Maladie> listeMalNain = new ArrayList<>();

        Elfe elfe = new Elfe("Legolas", "Homme", 100, 180, 120, 3, listeMalElfe);
        Nain nain = new Nain("Gimli", "Homme", 120, 140, 150, 2, listeMalNain);

        ServiceMedical serviceMedical = new ServiceMedical("Service Fantastique", 300, 5, new ArrayList<>(), "faible");
        serviceMedical.ajouterCreature(elfe);
        serviceMedical.ajouterCreature(nain);

        elfe.sEmporter(serviceMedical);
        assertTrue(nain.aMaladieContagieuse());
    }

    @Test
    public void testAttendreAvecEffetVIP() {
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Nain n1 = new Nain("Gimli ", "Homme", 100, 120, 139, 5, listeMal);
        n1.attendreAvecEffetVIP(true);
        assertEquals(3, n1.getMoralIndic());
    }

    @Test
    public void testAttendreEnTriage() {
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Orque orque1 = new Orque("Gorg", "Homme", 150, 200, 160, 5, listeMal);
        Orque orque2 = new Orque("Ugluk", "Femme", 150, 200, 160, 5, listeMal);

        ServiceMedical triage = new ServiceMedical("Triage", 300, 5, new ArrayList<>(), "faible");
        triage.ajouterCreature(orque1);
        triage.ajouterCreature(orque2);

        orque1.attendreEnTriage(triage, true);
        assertEquals(5, orque1.getMoralIndic()); // Moral change pas
    }
}
