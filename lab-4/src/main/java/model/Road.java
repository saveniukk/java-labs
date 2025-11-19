package model;

import model.people.Passenger;
import model.transport.Transport;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Transport<? extends Passenger>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Transport<? extends Passenger> transport){
        carsInRoad.add(transport);
    }

    public int getCountOfHumans() {
        int totalHumans = 0;
        for(Transport<? extends Passenger> transport : carsInRoad) {
            totalHumans += transport.getTakenSeats();
        }
        return totalHumans;
    }
}