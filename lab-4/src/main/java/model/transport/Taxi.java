package model.transport;

import model.people.Passenger;

public class Taxi extends Car<Passenger> {
    public Taxi(int maxSeat) {
        super(maxSeat);
    }
}