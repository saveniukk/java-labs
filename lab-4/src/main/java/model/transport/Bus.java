package model.transport;

import model.people.Passenger;

public class Bus extends Transport<Passenger> {
    public Bus(int maxSeat) {
        super(maxSeat);
    }
}