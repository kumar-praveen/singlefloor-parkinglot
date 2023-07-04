package org.example;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ParkingSpot {
    private ParkingSpotType type;
    private Vehicle vehicle;

    public ParkingSpot(ParkingSpotType type) {
        this.type = type;
        vehicle = null;
    }
}
