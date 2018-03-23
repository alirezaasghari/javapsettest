package at.refugeescode.javapsettest.pset4.controller;

import at.refugeescode.javapsettest.pset4.model.Luggage;
import at.refugeescode.javapsettest.pset4.util.TimeUtils;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirportTest {

    private Airport airport = new Airport();

    private Luggage luggage = new Luggage();


    @Test
    void acceptedLuggage() {

        luggage.setOwner("Ali");
        luggage.setDestination("Riga");
        luggage.setType("Backpack");
        luggage.setDropOffTime(TimeUtils.todayAt("23:10"));
        luggage.setDepartureTime(TimeUtils.todayAt("23:50"));
        luggage.setFlightDuration(TimeUtils.durationOfHours("6"));
        luggage.setWaitingDuration(Duration.ofSeconds(0));

        List<Luggage> departures = new ArrayList<>();
        departures.add(luggage);
        List<Luggage> arrival = airport.travel(departures);

        // One luggage arrives
        assertEquals(1, arrival.size());

        // The luggage has right waiting duration
        Luggage arrivalLuggage = departures.get(0);
        Duration expected = Duration.parse("PT7H25M");
        Duration waitingDuration = arrivalLuggage.getWaitingDuration();

        assertEquals(expected, waitingDuration);

        // The luggage has the right arrival time
        LocalDateTime arrivalTime = arrivalLuggage.getArrivalTime();
        LocalDateTime expectedArrival = TimeUtils.todayAt("05:50").plusDays(1);

        assertEquals(expectedArrival, arrivalTime);

    }

    @Test
    void rejectedLuggage() {

        luggage.setOwner("Rami");
        luggage.setDestination("Rome");
        luggage.setType("Baggage");
        luggage.setDropOffTime(TimeUtils.todayAt("21:50"));
        luggage.setDepartureTime(TimeUtils.todayAt("22:15"));
        luggage.setFlightDuration(TimeUtils.durationOfHours("3"));
        luggage.setWaitingDuration(Duration.ofSeconds(0));

        List<Luggage> rejectedLuggage = new ArrayList<>();
        rejectedLuggage.add(luggage);
        List<Luggage> noTravel = airport.travel(rejectedLuggage);
        assertEquals(0, noTravel.size());
    }
}

