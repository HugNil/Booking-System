package com.hugo.bookingsys;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FirebaseConnection fb = new FirebaseConnection();
        User user = new User("Test", "Test", "johndoe@gmail.com", "12345678", "Test Street", "Test City", "1234");
        Booking booking = new Booking(new ArrayList<>(Arrays.asList(user)), LocalDateTime.of(2024, 11, 22, 18, 30), 4, 800, "Test Test");
        booking.addBookingToFirebase(booking);
    }
}
