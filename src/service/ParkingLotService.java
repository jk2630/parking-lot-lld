package service;

import constants.Constants;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {

    ParkingLotBuilding parkingLot = new ParkingLotBuilding(Constants.DEFAULT_LOT_ID,null);
    ParkingLotFloorService floorService = new ParkingLotFloorService();
    List<ParkingLotTicket> tickets = new ArrayList<>();

    public void startApp(List<String> commands){
        for(String command : commands){
            switch (command.substring(0, command.indexOf(' '))) {
                case "create_parking_lot":
                    String details = command.substring(command.indexOf(' ') + 1);
                    String parkingLodId = details.substring(0, details.indexOf(' '));
                    Integer floors = Integer.parseInt(details.substring(details.indexOf(' ')+1,details.indexOf(' ')+2));
                    Integer slots = Integer.parseInt(details.substring(details.lastIndexOf(' ')+1));
                    createParkingLot(parkingLodId,floors,slots);
                    break;
                case "park_vehicle":
                    String details2 = command.substring(command.indexOf(' ') + 1);
                    String vehicle_type = details2.substring(0, details2.indexOf(' '));
                    String reg_no = details2.substring(details2.indexOf(' ')+1, details2.lastIndexOf(' '));
                    String color = details2.substring(details2.lastIndexOf(' ')+1);
                    parkVehicle(VEHICLE_TYPE.valueOf(vehicle_type),reg_no,color);
                    break;
                case "unpark_vehicle":
                    String details3 = command.substring(command.indexOf(' ') + 1);
                    unParkVehicle(details3);
                    break;
                case "display":
                    String details4 = command.substring(command.indexOf(' ') + 1);
                    String displayType = details4.substring(0, details4.indexOf(' '));
                    String vehicleType = details4.substring(details4.lastIndexOf(' ')+1);
                    displaySlots(displayType, VEHICLE_TYPE.valueOf(vehicleType));
                    break;
            }
        }
    }

    public void createParkingLot(String parkingLotId, Integer noOfFloors, Integer noOfSlotsPerFloor){
        List<ParkingLotFloor> floors = new ArrayList<>();
        for(int i=1;i<=noOfFloors;i++){
            floors.add(floorService.createFloor(i, noOfSlotsPerFloor));
        }
        this.parkingLot = new ParkingLotBuilding(parkingLotId, floors);
        System.out.println("Created parking lot with "+noOfFloors+" floors and "+noOfSlotsPerFloor+" slots per floor");
    }

    public void parkVehicle(VEHICLE_TYPE type, String registrationNumber, String color){
        ParkingLotTicket ticket = new ParkingLotTicket(Constants.DEFAULT_LOT_ID,type,registrationNumber,color);
        Integer[] floorAndSlot = getFreeSlot(type);
        Integer floor = floorAndSlot[0];
        Integer slot = floorAndSlot[1];
        if(floor == -1){
            System.out.println("Parking Lot Full");
            return;
        }
        parkingLot.getFloors().get(floor-1).getSlots().get(slot-1).setFilled(true);
        ticket.setTicketId(ticket.getTicketId() + "_" + floor.toString() + "_" + slot.toString());
        tickets.add(ticket);
        System.out.println("Parked vehicle. Ticket ID: " + ticket.getTicketId());
    }

    public Integer[] getFreeSlot(VEHICLE_TYPE type){
        Integer[] floorAndSlot = new Integer[2];
        for(ParkingLotFloor floor : parkingLot.getFloors()){
            for(ParkingLotSlot slot : floor.getSlots()){
                if(slot.getType() == type && !slot.getFilled()){
                    floorAndSlot[0] = floor.getFloorNumber();
                    floorAndSlot[1] = slot.getSlotNumber();
                    return floorAndSlot;
                }
            }
        }
        floorAndSlot[0] = -1;
        floorAndSlot[1] = -1;
        return floorAndSlot;
    }

    public void unParkVehicle(String ticketId){
        for(ParkingLotTicket ticket : tickets){
            if(ticket.getTicketId().equals(ticketId)){
                Integer[] floorAndSlot = getFloorAndSlotFromTicket(ticket.getTicketId());
                parkingLot.getFloors().get(floorAndSlot[0]-1).getSlots().get(floorAndSlot[1]-1).setFilled(false);
                System.out.println("Unparked vehicle with Registration Number: "+ticket.getRegistrationNumber()+" and Color: "+ticket.getColor());
                return;
            }
        }
        System.out.println("Invalid Ticket");
    }

    public Integer[] getFloorAndSlotFromTicket(String ticketId){
        Integer[] floorAndSlot = new Integer[2];
        floorAndSlot[0] = Integer.parseInt(ticketId.substring(ticketId.indexOf('_')+1, ticketId.indexOf('_')+2));
        floorAndSlot[1] = Integer.parseInt(ticketId.substring(ticketId.lastIndexOf('_')+1, ticketId.lastIndexOf('_')+2));
        return floorAndSlot;
    }

    public void displaySlots(String displayType, VEHICLE_TYPE vehicleType){
        switch(displayType){
            case "free_count" : displayFreeCount(vehicleType);
                break;
            case "free_slots" : displayFreeSlots(vehicleType);
                break;
            case "occupied_slots" : displayOccupiedSlots(vehicleType);
                break;
        }
    }

    private void displayFreeCount(VEHICLE_TYPE type){
        for(ParkingLotFloor floor : parkingLot.getFloors()){
            int count = 0;
            for(ParkingLotSlot slot : floor.getSlots()){
                if(slot.getType() == type && !slot.getFilled()){
                    count++;
                }
            }
            System.out.println("No. of free slots for "+type+" on Floor "+floor.getFloorNumber()+": "+count);
        }
    }

    private void displayFreeSlots(VEHICLE_TYPE type){
        for(ParkingLotFloor floor : parkingLot.getFloors()){
            List<String> freeSlotList = new ArrayList<>();
            for(ParkingLotSlot slot : floor.getSlots()){
                if(slot.getType() == type && !slot.getFilled()){
                    freeSlotList.add(slot.getSlotNumber().toString());
                }
            }
            if(freeSlotList.isEmpty()){
                System.out.println("Free slots for " + type + " on Floor " + floor.getFloorNumber() + ": " );
            }
            String resString = String.join(",", freeSlotList);
            System.out.println("Free slots for " + type + " on Floor " + floor.getFloorNumber() + ": " + resString);
        }
    }

    private void displayOccupiedSlots(VEHICLE_TYPE type){
        for(ParkingLotFloor floor : parkingLot.getFloors()){
            List<String> occupiedSlotList = new ArrayList<>();
            for(ParkingLotSlot slot : floor.getSlots()){
                if(slot.getType() == type && slot.getFilled()){
                    occupiedSlotList.add(slot.getSlotNumber().toString());
                }
            }
            if(occupiedSlotList.isEmpty()){
                System.out.println("Occupied slots for " + type + " on Floor " + floor.getFloorNumber() + ": " );
            }
            String resString = String.join(",", occupiedSlotList);
            System.out.println("Occupied slots for " + type + " on Floor " + floor.getFloorNumber() + ": " + resString);
        }
    }
}
