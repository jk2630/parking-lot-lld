package service;

import models.ParkingLotFloor;

public class ParkingLotFloorService implements ParkingLotFloorInterface {

    ParkingLotSlotService slotService = new ParkingLotSlotService();
    @Override
    public ParkingLotFloor createFloor(Integer floorNumber, Integer noOfSlots) {
        return new ParkingLotFloor(floorNumber, slotService.createSlots(noOfSlots));
    }
}
