package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

/*
Changed By : kaviya kanakaraj

-> Movie constructor was updated to include Description
-> Updated discount method to include the new requirement

 */
public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;


    public Movie(String title, String description, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;

    }


    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }
    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) throws Exception {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(),showing);
    }

    private double getDiscount(int showSequence,Showing showing) throws Exception {

           double specialDiscount = 0;
           double dayTimeDiscount = 0;
           double sequenceDiscount = 0;
        try {
           if (MOVIE_CODE_SPECIAL == specialCode) {
               specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
           }

           if (showing.getStartTime().getHour() >= 11 & showing.getStartTime().getHour() <= 16) {
               dayTimeDiscount = ticketPrice * 0.25;
           }

           if (showSequence == 1) {
               sequenceDiscount = 3; // $3 discount for 1st show
           } else if (showSequence == 2) {

               sequenceDiscount = 2; // $2 discount for 2nd show
           } else if (showSequence == 7) {

               sequenceDiscount = 1; // $1 discount for 7th show
           }
       }
        catch(Exception e){
            throw new Exception("Exception while calculating discount: "+e);
        }
        // biggest discount wins
        return sequenceDiscount > (specialDiscount > dayTimeDiscount ? specialDiscount : dayTimeDiscount) ? sequenceDiscount : ((specialDiscount > dayTimeDiscount) ? specialDiscount : dayTimeDiscount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}