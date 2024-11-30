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
        Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 5, listeMal);
        n1.attendre();
        int moral2 = n1.getMoralIndic();
        assertEquals(4, moral2);
    }

    @Test
    public void testHurler() {
        // Préparation : Capturer la sortie standard
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Sauvegarder la sortie standard originale
        System.setOut(new PrintStream(outputStream)); // Rediriger la sortie standard vers un flux en mémoire

        try {
            // Appel de la méthode à tester
            ArrayList<Maladie> listeMal = new ArrayList<>();
            Maladie malaria = new Maladie("Malaria", "mala", 2, 5, true);
            Maladie corida = new Maladie("Corida", "cda", 1, 5,true);
            listeMal.add(malaria);
            listeMal.add(corida);
            Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 3, listeMal);
            n1.hurler();

            // Vérification : Comparer la sortie avec la chaîne attendue
            String expectedOutput = "UNE MINE !!!"; // Attention au saut de ligne
            assertEquals(expectedOutput, outputStream.toString());
        } finally {
            // Nettoyage : Restaurer la sortie standard originale
            System.setOut(originalOut);
        }
    }

    /*@Test
    public void testSEmporter(){

    }*/

    @Test
    public void testTomberMalade(){
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5,true);
        Maladie corida = new Maladie("Corida", "cda", 1, 5,true);
        listeMal.add(malaria);
        listeMal.add(corida);
        Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 3, listeMal);
        ArrayList<String> l2;
        n1.tomberMalade();
        l2 = n1.getListeMaladies();
        String lActuel = l2.toString();
        String res1 = "[mala: Malaria, cda: Corida, ZPL: Zoopathie paraphénique lycanthropique]";
        String res2 = "[mala: Malaria, cda: Corida, FOMO: Syndrome fear of missing out]";
        String res3 = "[mala: Malaria, cda: Corida, DRS: Dépendance aux réseaux sociaux]";
        String res4 = "[mala: Malaria, cda: Corida, MDC: Maladie débilitante chronique]";
        String res5 = "[mala: Malaria, cda: Corida, PEC: Porphyrie érythropoïétique congénitale]";
        String res6 = "[mala: Malaria, cda: Corida, BG: Bégaiement Gustitif]";
        assertTrue(
            lActuel.equals(res1) || lActuel.equals(res2) || lActuel.equals(res3) || lActuel.equals(res4) ||
                    lActuel.equals(res5) || lActuel.equals(res6)
        );
    }

    @Test public void testEtreSoigne(){
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5,true);
        Maladie corida = new Maladie("Corida", "cda", 1, 5,true);
        listeMal.add(malaria);
        listeMal.add(corida);
        Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 3, listeMal);
        n1.etreSoigne();
        ArrayList<String> l2;
        l2 = n1.getListeMaladies();
        System.out.println(l2);
        String res1 = "[cda: Corida]";
        String res2 = "[mala: Malaria]";
        String res3 = "[mala: Malaria, cda: Corida]";
        String lActuel = l2.toString();
        assertTrue(
                lActuel.equals(res1) || lActuel.equals(res2) || lActuel.equals(res3)
        );
    }

    @Test
    public void testTrepassementCreature() {
        // Préparer les maladies
        ArrayList<Maladie> maladies = new ArrayList<>();
        maladies.add(new Maladie("Grippe", "GRP", 5, 5, true)); // Létale

        // Préparer une créature
        Creature creature = new Orque("Grug", "Mâle", 100, 180, 5, 1, maladies);

        // Préparer un service médical
        ArrayList<Creature> listeCreatures = new ArrayList<>();
        listeCreatures.add(creature);
        ServiceMedical serviceMedical = new ServiceMedical("Service Orc", 300, 5, listeCreatures, "faible");

        // Vérifier avant trépas
        assertEquals(1, serviceMedical.getNombreCreaturesPresente());

        // Faire trépasser la créature
        creature.trepasser(serviceMedical);

        // Vérifier après trépas
        assertEquals(0, serviceMedical.getNombreCreaturesPresente());
    }
}
