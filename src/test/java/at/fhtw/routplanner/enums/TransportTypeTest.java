package at.fhtw.routplanner.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportTypeTest {

    @Test
    void testToString() {
        assertEquals("foot-walking", TransportType.FootWalk.toString());
        assertEquals("foot-hiking", TransportType.FootHike.toString());
        assertEquals("wheelchair", TransportType.Wheelchair.toString());
        assertEquals("cycling-regular", TransportType.Bike.toString());
        assertEquals("cycling-road", TransportType.RoadBike.toString());
        assertEquals("cycling-mountain", TransportType.MountainBike.toString());
        assertEquals("cycling-electric", TransportType.EBike.toString());
        assertEquals("driving-car", TransportType.Car.toString());
        assertEquals("driving-hgv", TransportType.HeavyVehicle.toString());
    }

    @Test
    void testFromDisplayNameValid() {
        assertEquals(TransportType.FootWalk, TransportType.fromDisplayName("Walk"));
        assertEquals(TransportType.FootHike, TransportType.fromDisplayName("Hike"));
        assertEquals(TransportType.Wheelchair, TransportType.fromDisplayName("Barrier Free"));
        assertEquals(TransportType.Bike, TransportType.fromDisplayName("Bike"));
        assertEquals(TransportType.RoadBike, TransportType.fromDisplayName("Roadbike"));
        assertEquals(TransportType.MountainBike, TransportType.fromDisplayName("Mountain bike"));
        assertEquals(TransportType.EBike, TransportType.fromDisplayName("E-Bike"));
        assertEquals(TransportType.Car, TransportType.fromDisplayName("Car"));
        assertEquals(TransportType.HeavyVehicle, TransportType.fromDisplayName("Heavy Vehicle"));
    }

    @Test
    void testFromDisplayNameInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                TransportType.fromDisplayName("Unknown"));
        assertEquals("No enum constant with display name Unknown", exception.getMessage());
    }
}