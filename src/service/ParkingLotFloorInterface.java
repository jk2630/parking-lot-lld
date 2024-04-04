package service;

import models.ParkingLotFloor;

public interface ParkingLotFloorInterface {
    public ParkingLotFloor createFloor(Integer floorNumber, Integer noOfSlots);
}
