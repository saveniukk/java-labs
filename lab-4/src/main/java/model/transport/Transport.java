package model.transport;

import java.util.ArrayList;
import java.util.List;

import exceptions.PassengerNotFoundException;
import exceptions.NoSeatsException;
import model.people.Passenger;

public abstract class Transport<T extends Passenger> {
    private final int maxSeat;
    private final List<T> passengers;

    public Transport(int maxSeat) {
        this.maxSeat = maxSeat;
        this.passengers = new ArrayList<>(maxSeat);
    }

    public int getMaxSeat() {
        return maxSeat;
    }

    public int getTakenSeats() {
        return passengers.size();
    }

    public void addPassenger(T passenger) throws NoSeatsException {
        if (maxSeat <= passengers.size()) {
            throw new NoSeatsException("No seats available. " + passenger.getName() + " can't get on." );
        }
        passengers.add(passenger);
        System.out.println(passenger + " take on " + this.getClass().getSimpleName());
    }

    public void removePassenger(T passenger) throws PassengerNotFoundException {
        if (!passengers.contains(passenger)) {
            throw new PassengerNotFoundException("Passenger " + passenger.getName() + " not found in transport");
        }
        passengers.remove(passenger);
        System.out.println(passenger + " take off " + this.getClass().getSimpleName());
    }
}
