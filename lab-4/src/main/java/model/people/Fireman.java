package model.people;

public class Fireman extends Passenger {
    public Fireman(String name) {
        super(name);
    }

    public String toString() {
        return "Fireman{name='" + getName() + "'}";
    }
}
