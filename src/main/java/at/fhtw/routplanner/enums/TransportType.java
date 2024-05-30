package at.fhtw.routplanner.enums;

public enum TransportType {

    FootWalk("foot-walking"),
    FootHike("foot-hiking"),
    Wheelchair("wheelchair"),
    Bike("cycling-regular"),
    RoadBike("cycling-road"),
    MountainBike("cycling-mountain"),
    EBike("cycling-electric"),
    Car("driving-car"),
    HeavyVehicle("driving-hgv");

    private final String displayName;
    TransportType(String displayName){
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return displayName;
    }
    public static TransportType fromDisplayName(String displayName) {
        for (TransportType transportType : TransportType.values()) {
            if (transportType.displayName.equalsIgnoreCase(displayName)) {
                return transportType;
            }
        }
        throw new IllegalArgumentException("No enum constant with display name " + displayName);
    }
}
