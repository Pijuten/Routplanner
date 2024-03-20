package at.fhtw.routplanner.enums;

public enum TransportType {

    FootWalk("Walk"),
    FootHike("Hike"),
    Wheelchair("Barrier Free"),
    Bike("Bike"),
    RoadBike("Roadbike"),
    MountainBike("Mountain bike"),
    EBike("E-Bike"),
    Car("Car"),
    HeavyVehicle("Heavy Vehicle");

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
