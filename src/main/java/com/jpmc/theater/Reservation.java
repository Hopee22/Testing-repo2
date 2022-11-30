package com.jpmc.theater;
/*
Changed By: kaviya kanakaraj

-> created a new Id variable
-> created a new method showingDiscountFee to calculate the discountFee

 */
public class Reservation {
    private int id;
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(int id,Customer customer, Showing showing, int audienceCount) {
        this.id=id;
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }
    public Showing getShowing(){
        return showing;
    }

    public int getId(){
        return id;
    }
    public Customer getCustomer() {
        return customer;
    }

    public double totalFee() {
        return showing.getMovieFee() * audienceCount;
    }

    public double showingDiscountFee() throws Exception { return showing.calculateFee(audienceCount); }
}