package org.example;

import java.util.Scanner;

public class ParkingApplication {
    public static void main(String[] args) {
        System.out.println("Starting parking service !!");
        System.out.println("Enter total number of operations..");
        Scanner scn = new Scanner(System.in);
        int operation = scn.nextInt();

        ParkingLot parkingLot = new ParkingLot(3,3,2);
        while(operation > 0){
            System.out.println("Enter 1 to park and 2 to remove..");
            int val = scn.nextInt();
            Vehicle vehicle = ParkingLot.getRandomVehicle();
            System.out.println(vehicle);

            if(val == 1){
                parkingLot.addVehicle(vehicle);
            }else{
                parkingLot.removeVehicle(vehicle);
            }
            operation--;
        }
        scn.close();
    }
}