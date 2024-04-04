package models;

public class ParkingLotSlot {
    private VEHICLE_TYPE type;
    private Integer slotNumber;
    private Boolean isFilled;

    public ParkingLotSlot(VEHICLE_TYPE type, Integer slotNumber, Boolean isFilled) {
        this.type = type;
        this.slotNumber = slotNumber;
        this.isFilled = isFilled;
    }

    public VEHICLE_TYPE getType() {
        return type;
    }

    public void setType(VEHICLE_TYPE type) {
        this.type = type;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Boolean getFilled() {
        return isFilled;
    }

    public void setFilled(Boolean filled) {
        isFilled = filled;
    }
}
