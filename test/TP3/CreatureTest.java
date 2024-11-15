package TP3;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CreatureTest {
    @Test
    public void testAttendre() {
        Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 5);
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
            Nain n1 = new Nain("Gimli LeNain", "Homme", 100, 120, 139, 3);
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

    /*@Test
    public void tomberMalade(){

    }*/


}
