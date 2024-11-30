package TP3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CrypteTest {

    @Test
    public void testGettersSetters() {
        ArrayList<Creature> listeCreatures = new ArrayList<>();
        Crypte crypte = new Crypte("Crypte Froide", 500, 10, listeCreatures, "faible", 3, -10);

        assertEquals(3, crypte.getNivVent());
        assertEquals(-10, crypte.getTemp());

        crypte.setNivVent(5);
        crypte.setTemp(-5);

        assertEquals(5, crypte.getNivVent());
        assertEquals(-5, crypte.getTemp());
    }
}
