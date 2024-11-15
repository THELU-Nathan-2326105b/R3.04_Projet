package TP3;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreatureTest {
    @Test
    public void testAttendre() {
        ArrayList<Maladie> listeMal = new ArrayList();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5);
        Maladie corida = new Maladie("Corida", "cda", 1, 5);
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
            ArrayList<Maladie> listeMal = new ArrayList();
            Maladie malaria = new Maladie("Malaria", "mala", 2, 5);
            Maladie corida = new Maladie("Corida", "cda", 1, 5);
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
        ArrayList<Maladie> listeMal = new ArrayList();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5);
        Maladie corida = new Maladie("Corida", "cda", 1, 5);
        listeMal.add(malaria);
        listeMal.add(corida);
        Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 3, listeMal);
        ArrayList<Maladie> l1;
        ArrayList<Maladie> l2;
        l1 = n1.getListeMaladies();
        n1.tomberMalade();
        l2 = n1.getListeMaladies();
        assertEquals(l1, l2);
    }


}
