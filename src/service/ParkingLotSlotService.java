package service;

import models.ParkingLotSlot;
import models.VEHICLE_TYPE;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSlotService implements ParkingLotSlotInterface{
    @Override
    public ParkingLotSlot createSlot(VEHICLE_TYPE type, Integer slotNumber) {
        return new ParkingLotSlot(type, slotNumber, false);
    }

    @Override
    public List<ParkingLotSlot> createSlots(Integer n) {
        List<ParkingLotSlot> listOfSlots = new ArrayList<>();
        if(n == 1){
            listOfSlots.add(createSlot(VEHICLE_TYPE.TRUCK, 1));
            return listOfSlots;
        }
        if(n == 2){
            listOfSlots.add(createSlot(VEHICLE_TYPE.TRUCK, 1));
            listOfSlots.add(createSlot(VEHICLE_TYPE.BIKE, 2));
            return listOfSlots;
        }
        if(n == 3){
            listOfSlots.add(createSlot(VEHICLE_TYPE.TRUCK, 1));
            listOfSlots.add(createSlot(VEHICLE_TYPE.BIKE, 2));
            listOfSlots.add(createSlot(VEHICLE_TYPE.BIKE, 3));
            return listOfSlots;
        }
        listOfSlots.add(createSlot(VEHICLE_TYPE.TRUCK, 1));
        listOfSlots.add(createSlot(VEHICLE_TYPE.BIKE, 2));
        listOfSlots.add(createSlot(VEHICLE_TYPE.BIKE, 3));
        for(Integer i=4;i<=n;i++){
            listOfSlots.add(createSlot(VEHICLE_TYPE.CAR, i));
        }
        return listOfSlots;
    }
}
