package org.example;

import java.util.Random;

public class ParkingLot {
    private final ParkingSpot[] large;
    private final ParkingSpot[] compact;
    private final ParkingSpot[] bike;
    private static final Random random = new Random();


    public ParkingLot(int largeFreeSpot, int compactFreeSpot, int bikeFreeSpot){
        large = new ParkingSpot[largeFreeSpot];
        compact = new ParkingSpot[compactFreeSpot];
        bike = new ParkingSpot[bikeFreeSpot];
    }


    public void addVehicle(Vehicle vehicle){
        VehicleType type = vehicle.getType();
        int idx = getParkingSpot(type);
        if(!isValidSpot(idx, type)){
            System.out.println("Spot not available..");
            return;
        }
        parkInSpot(vehicle, type, idx);
        System.out.println("Parked at spot: "+idx);
        printStatus(type);
    }

    public void parkInSpot(Vehicle vehicle, VehicleType type, int idx){
        if(VehicleType.BIKE.equals(type)){
            parkVehicle(vehicle,ParkingSpotType.BIKE,bike,idx);
            return;
        }else if(VehicleType.CAR.equals(type)){
            parkVehicle(vehicle,ParkingSpotType.COMPACT,bike,idx);
            return;
        }
        parkVehicle(vehicle,ParkingSpotType.LARGE,bike,idx);
    }

    private void parkVehicle(Vehicle vehicle, ParkingSpotType type, ParkingSpot[] spots, int idx){
        if(spots[idx] == null){
            spots[idx] = new ParkingSpot(type);
        }
        spots[idx].setVehicle(vehicle);
    }

    private boolean isValidSpot(int idx, VehicleType type){
        if(VehicleType.BIKE.equals(type)){
            return idx < bike.length;
        }else if(VehicleType.CAR.equals(type)){
            return idx < compact.length;
        }
        return idx < large.length;
    }

    private int getParkingSpot(VehicleType type){
        if(VehicleType.BIKE.equals(type)){
            return getAvailableSpot(bike);
        }else if(VehicleType.CAR.equals(type)){
            return getAvailableSpot(compact);
        }
        return getAvailableSpot(large);
    }

    private int getAvailableSpot(ParkingSpot[] spots){
        for(int idx = 0;idx<spots.length;idx++){
            if(spots[idx] == null || spots[idx].getVehicle() == null) return idx;
        }
        return spots.length;
    }

    public void removeVehicle(Vehicle vehicle){
        VehicleType type = vehicle.getType();
        int idx = getParkingSpot(vehicle);
        if(!isValidSpot(idx, vehicle.getType())){
            System.out.println("No vehicle available..");
            return;
        }
        removeFromSpot(idx, type);
        System.out.println("Removed vehicle from "+idx);
        printStatus(type);
    }

    private int getParkingSpot(Vehicle vehicle){
        VehicleType type = vehicle.getType();
        if(VehicleType.BIKE.equals(type)){
            return getParkingSpot(vehicle, bike);
        }else if(VehicleType.CAR.equals(type)){
            return getParkingSpot(vehicle, compact);
        }
        return getParkingSpot(vehicle, large);
    }

    private int getParkingSpot(Vehicle vehicle, ParkingSpot[] spots){
        for(int idx=0;idx<spots.length;idx++){
            if(spots[idx]!= null && vehicle.getNumber().equals(spots[idx].getVehicle().getNumber())){
                return idx;
            }
        }
        return spots.length;
    }

    private void removeFromSpot(int idx, VehicleType type){
        if(VehicleType.BIKE.equals(type)){
            removeVehicle(idx, bike);
            return;
        }else if(VehicleType.CAR.equals(type)){
            removeVehicle(idx, compact);
            return;
        }
        removeVehicle(idx, large);
    }

    private void removeVehicle(int idx, ParkingSpot[] spots){
        spots[idx] = null;
    }

    private static VehicleType getRandomVehicleType(){
        int idx = random.nextInt(3);
        if(idx == 0) return VehicleType.BIKE;
        else if(idx == 1) return VehicleType.CAR;
        return VehicleType.TRUCK;
    }
    public static Vehicle getRandomVehicle(){
        return new Vehicle(String.valueOf(random.nextInt(10)), getRandomVehicleType());
    }

    private void printStatus(VehicleType type){
        if(VehicleType.BIKE.equals(type)){
            printParkingStatus(type, bike);
        }else if(VehicleType.CAR.equals(type)){
            printParkingStatus(type, bike);
        }else{
            printParkingStatus(type, bike);
        }
    }

    private void printParkingStatus(VehicleType type, ParkingSpot[] spots){
        System.out.print(type+": [ ");
        for(int idx=0;idx<spots.length;idx++){
            System.out.print(spots[idx]+" ");
        }
        System.out.println("  ]");
    }
}
