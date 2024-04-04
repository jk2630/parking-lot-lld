package models;

import java.util.List;

public class ParkingLotFloor {
    private Integer floorNumber;
    private List<ParkingLotSlot> slots;

    public ParkingLotFloor(Integer floorNumber, List<ParkingLotSlot> slots) {
        this.floorNumber = floorNumber;
        this.slots = slots;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingLotSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<ParkingLotSlot> slots) {
        this.slots = slots;
    }
}
