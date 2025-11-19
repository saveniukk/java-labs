import exceptions.PassengerNotFoundException;
import exceptions.NoSeatsException;
import model.Road;
import model.people.Fireman;
import model.people.Passenger;
import model.people.Policeman;
import model.transport.Bus;
import model.transport.Firetruck;
import model.transport.PoliceCar;
import model.transport.Taxi;

public class Main {

    public static void main(String[] args) {
        Passenger passenger1 = new Passenger("Mary");
        Fireman firemanBob = new Fireman("Bob");
        Policeman officerJohn = new Policeman("John");

        Bus bus = new Bus(30);
        Taxi taxi = new Taxi(2);
        Firetruck firetruck = new Firetruck(4);
        PoliceCar policeCar = new PoliceCar(2);

        try {
            System.out.println(">>> Посадка пасажирів <<<");

            bus.addPassenger(passenger1);
            bus.addPassenger(firemanBob);

            firetruck.addPassenger(firemanBob);

            policeCar.addPassenger(officerJohn);

            taxi.addPassenger(passenger1);

            // policeCar.addPassenger(passenger1); // Викличе помилку компіляції, бо звичайний пасажир не може сісти до поліцейської машини

        } catch (NoSeatsException e) {
            System.err.println("Error during boarding passengers: " + e.getMessage());
        }

        System.out.println("\n>>> Перевірка дороги <<<");

        Road kyivRoad = new Road();
        kyivRoad.addCarToRoad(bus);
        kyivRoad.addCarToRoad(firetruck);
        kyivRoad.addCarToRoad(policeCar);
        kyivRoad.addCarToRoad(taxi);

        int totalPeople = kyivRoad.getCountOfHumans();
        System.out.println("Number of people on road: " + totalPeople);


        System.out.println("\n>>> Тестування помилок <<<");

        try {
            System.out.println("Trying to put person in full taxi...");
            taxi.addPassenger(officerJohn); // Займає останнє місце (2/2)
            taxi.addPassenger(new Passenger("Kev"));
        } catch (NoSeatsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("Trying to remove person that doesn't exist...");
            bus.removePassenger(new Passenger("Tory"));
        } catch (PassengerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}