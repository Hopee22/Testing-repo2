package com.jpmc.theater;
import org.json.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
Changed By: kaviya kanakaraj

->updated the theater constructor to just initialize the localdateprovider internally.
->Created separate methods for movie and showing initialization
->Created a new function to retrieve movie object based on title
-> Removed the plural function
-> Created new generateReceipt method
-> Added Exception Handling to all the methods
*/

public class Theater {

    LocalDateProvider localDateProvider;
    private List<Showing> schedule;
    private List<Movie> allMovies;

    public Theater() {
        this.localDateProvider = LocalDateProvider.singleton();
        createMovie();
        createShowing();
    }

    private void createMovie(){
        allMovies = List.of(new Movie("Spider-Man: No Way Home","Marvel", Duration.ofMinutes(90), 12.5, 1),
                            new Movie("Turning Red","Animation", Duration.ofMinutes(85), 11, 0),
                            new Movie("The Batman","DC", Duration.ofMinutes(95), 9, 0)
        );
    }

    private Movie getMovieByTitle(String movieTitle) throws Exception {
        try {
            for (Movie movie : allMovies) {
                if ((movie.getTitle()).equalsIgnoreCase(movieTitle)) {
                    return movie;
                }
            }
        }
        catch(Exception e){
            throw new Exception("Exception while Retriving Movie");
        }
        return null;
    }

    private void createShowing(){
        try {
            Movie spiderMan = getMovieByTitle("Spider-Man: No Way Home");
            Movie turningRed = getMovieByTitle("turning red");
            Movie theBatMan = getMovieByTitle("the batman");

            schedule = List.of(
                    new Showing(turningRed, 1, LocalDateTime.of(localDateProvider.currentDate(), LocalTime.of(9, 0))),
                    new Showing(spiderMan, 2, LocalDateTime.of(localDateProvider.currentDate(), LocalTime.of(11, 0))),
                    new Showing(theBatMan, 3, LocalDateTime.of(localDateProvider.currentDate(), LocalTime.of(12, 50))),
                    new Showing(turningRed, 4, LocalDateTime.of(localDateProvider.currentDate(), LocalTime.of(14, 30))),
                    new Showing(spiderMan, 5, LocalDateTime.of(localDateProvider.currentDate(), LocalTime.of(16, 10))),
                    new Showing(theBatMan, 6, LocalDateTime.of(localDateProvider.currentDate(), LocalTime.of(17, 50))),
                    new Showing(turningRed, 7, LocalDateTime.of(localDateProvider.currentDate(), LocalTime.of(19, 30))),
                    new Showing(spiderMan, 8, LocalDateTime.of(localDateProvider.currentDate(), LocalTime.of(21, 10))),
                    new Showing(theBatMan, 9, LocalDateTime.of(localDateProvider.currentDate(), LocalTime.of(23, 0)))
            );
        }
        catch(NullPointerException e){
            throw new NullPointerException("Wrong title name in the search:"+e);
        } catch (Exception e) {
            throw new RuntimeException("Wrong title name in the search:"+e);
        }
    }
    public Reservation reserve(int id,Customer customer, int sequence, int howManyTickets)  {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(id,customer, showing, howManyTickets);
    }

    public void printSchedule() {
        System.out.println(localDateProvider.currentDate());
        System.out.println("===================================================");

            schedule.forEach(s ->
                {
                    try {
                        System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        System.out.println("===================================================");
    }

    private String humanReadableFormat(Duration duration) throws Exception {
        long hour = 0;
        long remainingMin = 0;
        try {
            hour = duration.toHours();
            remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
        }
        catch(Exception e){
            throw new Exception("Exception while converting duration to humanReadable Format: "+ e);
        }
        return String.format("(%s hour%s %s minute%s)", hour, hour==1?"":"s", remainingMin, remainingMin==1?"":"s");
    }

    public void generateReceipt(Reservation reservation) throws Exception {

        System.out.println("\n ##### Receipt #####");
        System.out.println("Reservation ID :" + reservation.getId());
        System.out.println("####### Text Receipt #######");
        System.out.println(reservation.getCustomer().getName() + "'s Total Fee: " + reservation.totalFee());
        System.out.println(reservation.getCustomer().getName() + "'s Discounted Fee: " + reservation.showingDiscountFee());

        System.out.println("####### JSON Receipt #######");
        JSONObject receiptJsonObject = new JSONObject();
        receiptJsonObject.put("Customer ID", reservation.getCustomer().getId());
        receiptJsonObject.put("Customer Name", reservation.getCustomer().getName());
        receiptJsonObject.put("Movie Name", reservation.getShowing().getMovie().getTitle());
        receiptJsonObject.put("Movie Description", reservation.getShowing().getMovie().getDescription());
        receiptJsonObject.put("Movie Running Time", reservation.getShowing().getMovie().getRunningTime().toMinutes());
        receiptJsonObject.put("Show StartTime ", reservation.getShowing().getStartTime());
        receiptJsonObject.put("Ticket Fee", reservation.totalFee());
        receiptJsonObject.put("Discounted Fee", reservation.showingDiscountFee());

        System.out.println(receiptJsonObject.toString());
    }


    public static void main(String[] args) throws Exception {
        try {
            Theater theater = new Theater();
            theater.printSchedule();
            Customer customer1 = new Customer("Tom", "101");
            Reservation reservation1 = theater.reserve(111,customer1, 2, 2);
            theater.generateReceipt(reservation1);

            Customer customer2 = new Customer("Andrea", "102");
            Reservation reservation2 = theater.reserve(112,customer1, 7, 4);
            theater.generateReceipt(reservation2);

        }
        catch(Exception e){
            throw new Exception("Caught Exception while making reservation:  "+e.getMessage());
        }
        finally{
            System.out.println("\n Thank You! Visit Again");
        }
    }
}
