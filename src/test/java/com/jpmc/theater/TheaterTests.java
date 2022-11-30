package com.jpmc.theater;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {

    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater();
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(101, john, 2, 4);
        assertEquals(reservation.totalFee(), 50);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater();
        theater.printSchedule();
    }

    @Test
    void testGenerateReceipt() throws Exception {
        Theater theater = new Theater();
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(101, john, 2, 4);
        theater.generateReceipt(reservation);


    }



}
