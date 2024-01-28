package Board;

import org.junit.Test;

import static org.junit.Assert.*;

public class KoPlaceTest {

    @Test
    public void testGetKoPlace() {
        KoPlace koPlace = new KoPlace();
        koPlace.setKo(6, 5);
        assertEquals(6, koPlace.getX());
        assertEquals(5, koPlace.getY());
        assertTrue(koPlace.getIsKo());
    }

    @Test
    public void testSetKoFalse() {
        KoPlace koPlace = new KoPlace();
        koPlace.setKo(6, 5);
        assertTrue(koPlace.getIsKo());
        assertEquals(6, koPlace.getX());
        assertEquals(5, koPlace.getY());
        koPlace.setKoFalse();
        assertFalse(koPlace.getIsKo());
    }
}
