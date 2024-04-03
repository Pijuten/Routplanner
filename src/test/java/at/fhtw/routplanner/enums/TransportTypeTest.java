package at.fhtw.routplanner.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportTypeTest {

    @Test
    void testToString() {
        assertEquals("Walk", TransportType.FootWalk.toString());
        assertEquals("Hike", TransportType.FootHike.toString());
        assertEquals("Barrier Free", TransportType.Wheelchair.toString());
        assertEquals("Bike", TransportType.Bike.toString());
        assertEquals("Roadbike", TransportType.RoadBike.toString());
        assertEquals("Mountain bike", TransportType.MountainBike.toString());
        assertEquals("E-Bike", TransportType.EBike.toString());
        assertEquals("Car", TransportType.Car.toString());
        assertEquals("Heavy Vehicle", TransportType.HeavyVehicle.toString());
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