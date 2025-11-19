package model.people;

public class Policeman extends Passenger {
    public Policeman(String name) {
        super(name);
    }

    public String toString() {
        return "Policeman{name='" + getName() + "'}";
    }
}