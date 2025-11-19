package model.transport;

import model.people.Passenger;

public abstract class Car<T extends Passenger> extends Transport<T> {
    public Car(int maxSeat) {
        super(maxSeat);
    }
}