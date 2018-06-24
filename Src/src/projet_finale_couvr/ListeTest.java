package projet_finale_couvr;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe permettant de tester les differentes methodes de la classe Liste.java avec JUnit4.
 */
public class ListeTest {
    Liste<Integer> l=new Liste<Integer>();
    Liste<Integer> l1=new Liste<Integer>();

    /**
     * methode pour tester add
     */
    @org.junit.Test
    public void testAdd() {
        l.add(5).add(10).add(3);
        Assert.assertEquals(l,l1.add(3).add(10).add(5));
    }

    /**
     * methode pour tester contains
     */
    @Test
    public void testContains() {
        Assert.assertEquals(true,l.add(5).contains(5));
        Assert.assertEquals(false,l.add(5).contains(3));
    }

    /**
     * methode pour tester getTete()
     */
    @Test
    public void testGetTete() {
        Maillon<Integer> m=new Maillon<Integer>(3);
        Assert.assertEquals(m.getValeur(),l.add(5).add(10).add(3).getTete().getValeur());
    }

    /**
     * methode pour tester isEmpty()
     */
    @Test
    public void testIsEmpty() {
        Assert.assertEquals(true,l.isEmpty());
        Assert.assertEquals(false,l.add(5).isEmpty());

    }

    /**
     * methode pour tester size
     */
    @Test
    public void testSize() {
        Assert.assertEquals(3,l.add(5).add(10).add(3).size());
    }

    /**
     * methode pour tester equals
     */
    @Test
    public void testEquals() {
        Assert.assertEquals(true,l.add(5).add(10).add(3).equals(l1.add(3).add(10).add(5)));
        Assert.assertEquals(false,l.add(5).add(10).add(3).equals(l1.add(5).add(10).add(4)));

    }

}