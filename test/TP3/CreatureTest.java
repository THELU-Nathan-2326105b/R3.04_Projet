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
    public void testTrepassementAvecDemoralisation() {
        // Préparer les maladies
        ArrayList<Maladie> maladiesLethales = new ArrayList<>();
        Maladie maladieLetale = new Maladie("Grippe", "GRP", 5, 5, true); // Létale
        maladiesLethales.add(maladieLetale);

        ArrayList<Maladie> maladiesNonLethales = new ArrayList<>();
        Maladie maladieBenigne = new Maladie("Rhume", "RH", 2, 5, true); // Non létale
        maladiesNonLethales.add(maladieBenigne);

        // Préparer les créatures
        Creature elfe = new Elfe("Legolas", "Homme", 100, 180, 120, 3, maladiesLethales);
        Creature orque = new Orque("Gorg", "Orque", 150, 200, 160, 2, maladiesNonLethales);
        Creature nain = new Nain("Gimli", "Homme", 120, 140, 150, 2, maladiesNonLethales);

        // Préparer un service médical
        ArrayList<Creature> listeCreatures = new ArrayList<>();
        listeCreatures.add(elfe);
        listeCreatures.add(orque);
        listeCreatures.add(nain);
        ServiceMedical serviceMedical = new ServiceMedical("Service Fantastique", 300, 5, listeCreatures, "faible");

        // Vérifier les indices de moral avant trépas
        assertEquals(3, elfe.getMoralIndic());
        assertEquals(2, orque.getMoralIndic());
        assertEquals(2, nain.getMoralIndic());

        // Faire trépasser l'Elfe
        elfe.trepasser(serviceMedical);

        // Vérifier que l'Elfe est retiré du service médical
        assertEquals(2, serviceMedical.getNombreCreaturesPresente());

        // Vérifier que le moral des autres créatures a diminué de -1
        assertEquals(1, orque.getMoralIndic());
        assertEquals(1, nain.getMoralIndic());
    }


}
