package models;

import java.util.List;

public class ParkingLotBuilding {
    private String id;
    private List<ParkingLotFloor> floors;

    public ParkingLotBuilding(String id, List<ParkingLotFloor> floors) {
        this.id = id;
        this.floors = floors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ParkingLotFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<ParkingLotFloor> floors) {
        this.floors = floors;
    }
}
