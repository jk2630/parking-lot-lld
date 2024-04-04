package service;

import models.ParkingLotSlot;
import models.VEHICLE_TYPE;

import java.util.List;

public interface ParkingLotSlotInterface {
    public ParkingLotSlot createSlot(VEHICLE_TYPE type, Integer slotNumber);
    public List<ParkingLotSlot> createSlots(Integer n);
}
