package com.hugo.bookingsys;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking {
    ArrayList <User> users;
    LocalDateTime date;
    int lanes;
    int price;
    String referenceName;

    public Booking(ArrayList<User> users, LocalDateTime date, int lanes, int price, String referenceName) {
        this.users = users;
        this.date = date;
        this.lanes = lanes;
        this.price = price;
        this.referenceName = referenceName;
    }

    public void addBookingToFirebase(Booking booking) {
        FirebaseConnection.addBooking(booking.date, booking.lanes, booking.price, booking.referenceName, booking.users);
    }
}