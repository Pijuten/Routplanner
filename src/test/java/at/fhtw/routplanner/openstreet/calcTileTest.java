package at.fhtw.routplanner.openstreet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcTileTest {
    @Test
    @DisplayName("The same point should be maxed in")
    void getZoomSamePointTest(){
        Integer ZoomFactor = CalcTile.getZoom(16.3128,48.1858,16.3128,48.1858);
        assertEquals(ZoomFactor,18);
    }
    @Test
    @DisplayName("A null point should return 0")
    public void test_null_coordinates() {
        Integer zoomFactor = CalcTile.getZoom(null, null, null, null);
        assertEquals(0, zoomFactor);
    }
}