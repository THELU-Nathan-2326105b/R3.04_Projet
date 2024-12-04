package TP3;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaladieTest {
    @Test
    public void testCreationMaladie() {
        // Test de la création de la maladie
        Maladie maladie = new Maladie("Maladie Débilitante Chronique", "MDC", 1,5, true);
        assertEquals("Maladie Débilitante Chronique", maladie.getNomComplet());
        assertEquals("MDC", maladie.getNomAbrege());
        assertEquals(1, maladie.getNiveauActuel());
        assertEquals(5, maladie.getNiveauMaximum());
    }
    @Test
    public void testAugmenterNiveau() {
        Maladie maladie = new Maladie("Maladie Débilitante Chronique", "MDC",1, 5,true);
        maladie.augmenterNiveau(3);
        assertEquals(4, maladie.getNiveauActuel());

        maladie.augmenterNiveau(5);
        assertEquals(5, maladie.getNiveauActuel());
    }
    @Test
    public void testEstLetale() {
        Maladie maladie = new Maladie("Maladie Débilitante Chronique", "MDC", 1, 5,true);
        assertFalse(maladie.estLetale());

        maladie.augmenterNiveau(5);
        assertTrue(maladie.estLetale());
    }

    @Test
    public void testDiminueNiveau() {
        Maladie maladie = new Maladie("Maladie Débilitante Chronique", "MDC", 1, 5,true);
        maladie.augmenterNiveau(2);
        maladie.diminuerNiveau(1);
        assertEquals(2, maladie.getNiveauActuel());
        maladie.diminuerNiveau(1);
        assertEquals(1, maladie.getNiveauActuel());
    }
}
