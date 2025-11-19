package model.transport;

import model.people.Policeman;

public class PoliceCar extends Car<Policeman>{
    public PoliceCar(int maxSeat) {
        super(maxSeat);
    }
}