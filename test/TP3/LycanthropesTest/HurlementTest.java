package TP3.LycanthropesTest;

import TP3.Lycanthropes.Hurlement;
import TP3.Lycanthropes.Lycanthrope;
import TP3.Maladie;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HurlementTest {
    @Test
    public void testAfficherCarac(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Sauvegarder la sortie standard originale
        System.setOut(new PrintStream(outputStream)); // Rediriger la sortie standard vers un flux en mémoire
        Hurlement h1 = getHurlement();
        try{
            // Appel à la méthode afficheCaracteristique
            h1.afficherCaracteristiques();

            // Vérification de l'affichage
            String output = outputStream.toString();
            assertTrue(output.contains("Hurlement émis par : Dinglu LaPute"));
            assertTrue(output.contains("Appartenance : Solitaire"));
        }
        finally {
            // Réinitialiser la sortie standard
            System.setOut(originalOut);
        }
        h1.afficherCaracteristiques();
    }

    private static Hurlement getHurlement() {
        ArrayList<Maladie> listeMal = new ArrayList<>();
        Maladie malaria = new Maladie("Malaria", "mala", 2, 5, true);
        Maladie corida = new Maladie("Corida", "cda", 1, 5,true);
        listeMal.add(malaria);
        listeMal.add(corida);
        Lycanthrope dinglu = new Lycanthrope(
                "Dinglu LaPute",
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
        Hurlement h1 = new Hurlement(dinglu);
        return h1;
    }
}
