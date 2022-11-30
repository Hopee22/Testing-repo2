package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWithSpecialPercentDiscount() throws Exception {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "Marvel",Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void specialMovieWithDayTimePercentDiscount() throws Exception {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "Marvel",Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
        assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void specialMovieWith1stDayDiscount() throws Exception {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "Marvel",Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(9.5, spiderMan.calculateTicketPrice(showing));
    }
    @Test
    void specialMovieWith2ndDayDiscount() throws Exception {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "Marvel",Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10.5, spiderMan.calculateTicketPrice(showing));
    }
    @Test
    void specialMovieWith7thDayDiscount() throws Exception {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "Marvel",Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(),LocalTime.now()));
        assertEquals(11.5, spiderMan.calculateTicketPrice(showing));
    }
}
