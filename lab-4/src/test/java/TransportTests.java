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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransportTests {

    @Test
    void shouldAddFiremanToFiretruck() {
        // before
        Firetruck firetruck = new Firetruck(2);
        Fireman fireman = new Fireman("John");

        // when
        assertDoesNotThrow(() -> firetruck.addPassenger(fireman));

        // then
        assertEquals(1, firetruck.getTakenSeats(), "Firetruck should have 1 passenger");
    }

    @Test
    void shouldAddPolicemanToPoliceCar() {
        // before
        PoliceCar policeCar = new PoliceCar(2);
        Policeman cop = new Policeman("Mary");

        // when
        assertDoesNotThrow(() -> policeCar.addPassenger(cop));

        // then
        assertEquals(1, policeCar.getTakenSeats(), "PoliceCar should have 1 passenger");
    }

    @Test
    void shouldAddVariousPassengersToTaxi() {
        // before
        Taxi taxi = new Taxi(5);
        Fireman fireman = new Fireman("Sam");
        Policeman cop = new Policeman("Rob");
        Passenger citizen = new Passenger("Kevin");

        // when
        assertDoesNotThrow(() -> {
            taxi.addPassenger(fireman);
            taxi.addPassenger(cop);
            taxi.addPassenger(citizen);
        });

        // then
        assertEquals(3, taxi.getTakenSeats(), "Taxi should add passengers of different types. It should have 3 passengers");
    }

    @Test
    void shouldThrowExceptionWhenVehicleIsFull() throws NoSeatsException {
        // before
        Taxi taxi = new Taxi(1);
        Passenger p1 = new Passenger("Mike");
        Passenger p2 = new Passenger("Jonny");
        taxi.addPassenger(p1);

        // when & then
        assertThrows(NoSeatsException.class, () -> {
            taxi.addPassenger(p2);
        }, "Error expected because all seats are taken");
    }

    @Test
    void shouldThrowExceptionWhenRemovingNonExistentPassenger() {
        // before
        Bus bus = new Bus(10);
        Passenger stranger = new Passenger("Ghost");

        // when & then
        assertThrows(PassengerNotFoundException.class, () -> {
            bus.removePassenger(stranger);
        }, "Error expected because the passenger ia not in the bus");
    }

    @Test
    void shouldSuccessfullyRemovePassenger() throws NoSeatsException, PassengerNotFoundException {
        // before
        Bus bus = new Bus(5);
        Passenger passenger = new Passenger("Anna");
        bus.addPassenger(passenger);
        int seatsBefore = bus.getTakenSeats();

        // when
        bus.removePassenger(passenger);

        // then
        assertEquals(1, seatsBefore);
        assertEquals(0, bus.getTakenSeats(), "After removing passenger, the number of taken seats should be decreased");
    }

    @Test
    void testRoadCounting() throws NoSeatsException {
        // before
        Road road = new Road();

        Bus bus = new Bus(10);
        bus.addPassenger(new Passenger("Max"));
        bus.addPassenger(new Passenger("Kira"));

        Firetruck ft = new Firetruck(2);
        ft.addPassenger(new Fireman("Ellen"));

        // when
        road.addCarToRoad(bus);
        road.addCarToRoad(ft);
        int totalHumans = road.getCountOfHumans();

        // then
        assertEquals(3, totalHumans, "Expected 3 people on the road");
    }

    @Test
    void testRoadCountingWithEmptyCars() {
        // before
        Road road = new Road();
        Taxi emptyTaxi = new Taxi(4);

        // when
        road.addCarToRoad(emptyTaxi);

        // then
        assertEquals(0, road.getCountOfHumans(), "Empty cars should add 0 people to the road");
    }
}