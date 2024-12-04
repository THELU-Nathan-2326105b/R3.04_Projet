package TP3.LycanthropesTest;

import TP3.Lycanthropes.Lycanthrope;
import TP3.Maladie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LycanthropeTest {
    @org.junit.jupiter.api.Test
    public void testAfficheCaracteristique() {
        // Rediriger la sortie standard pour capturer ce qui est affiché
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Création de la liste de maladies
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5,true);
        Maladie corida = new Maladie("Corida", "cda", 1, 5,true);
        listeMal.add(malaria);
        listeMal.add(corida);

        // Création du Lycanthrope
        Lycanthrope dinglu = new Lycanthrope(
                "Dinglu",
                "Vieux",
                "Male",
                600,
                180,
                5,
                listeMal,
                1500000,
                5,
                10,
                5,
                500000,
                "Solitaire"
        );

        try {

            // Appel à la méthode afficheCaracteristique
            dinglu.afficheCaracteristique();

            // Vérification de l'affichage
            String output = outputStream.toString();

            // Vérifiez que les caractéristiques sont bien affichées
            assertTrue(output.contains("NomComplet : Dinglu"));
            assertTrue(output.contains("Age : Vieux"));
            assertTrue(output.contains("Sexe : Male"));
            assertTrue(output.contains("Poids : 600"));
            assertTrue(output.contains("Taille : 180"));
            assertTrue(output.contains("MoralIndic : 5"));
            assertTrue(output.contains("Force : 1500000"));
            assertTrue(output.contains("FacteurDom : 5"));
            assertTrue(output.contains("RangDom : 10"));
            assertTrue(output.contains("Niveau : 5"));
            assertTrue(output.contains("Impetuosite : 500000"));
            assertTrue(output.contains("Meute : Solitaire"));
            assertTrue(output.contains("ListeMal"));
            assertTrue(output.contains("mala: Malaria"));
            assertTrue(output.contains("cda: Corida"));

        } finally {
            // Réinitialiser la sortie standard
            System.setOut(originalOut);
        }
    }

    @Test
    public void testHurler(){
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
            Lycanthrope dinglu = new Lycanthrope(
                    "Dinglu",
                    "vieux",
                    "Male",
                    600,
                    180,
                    5,
                    listeMal,
                    1500000,
                    5,
                    10,
                    5,
                    500000,
                    "Solitaire"
            );
            dinglu.hurler();

            // Vérification : Comparer la sortie avec la chaîne attendue
            String expectedOutput = "AAAAAAAAAAHHHHHHHHHH"; // Attention au saut de ligne
            Assertions.assertEquals(expectedOutput, outputStream.toString());
        } finally {
            // Nettoyage : Restaurer la sortie standard originale
            System.setOut(originalOut);
        }
    }

    @Test
    public void testEntendreHurlement(){

    }
}
