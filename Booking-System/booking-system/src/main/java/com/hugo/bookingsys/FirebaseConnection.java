package com.hugo.bookingsys;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;

public class FirebaseConnection {
    static Firestore db;
    public FirebaseConnection() {
        try {
            String serviceAccountKeyPath = "booking-system/src/main/java/com/hugo/resources/bookingsystemkey.json";
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(serviceAccountKeyPath));
            FirestoreOptions options = FirestoreOptions.newBuilder().setCredentials(credentials).build();
            // Initialize Firestore
            db = options.getService();

            // Test the connection
            System.out.println("Firestore connection established successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBooking(LocalDateTime date, int lanes, int price, String referenceName, ArrayList<User> users) {
        try {
            LocalDateTime bookingCreated = LocalDateTime.now();
            Map<String, Object> bookingData = new HashMap<>();
            bookingData.put("Date", date);
            bookingData.put("Lanes", lanes);
            bookingData.put("Price", price);
            bookingData.put("Reference name", referenceName);
            bookingData.put("Users", users);
            bookingData.put("Booking created", bookingCreated);

            DocumentReference docRef = db.collection("Bookings").document();
            ApiFuture<WriteResult> result = docRef.set(bookingData);

            System.out.println("Booking added at: " + result.get().getUpdateTime());
        } catch (java.lang.Error | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void addBookingTest() {
        LocalDateTime d = LocalDateTime.of(2024, 11, 22, 17, 30);
        int l = 3;
        int p = 150;
        String r = "Test6";
        ArrayList<User> u = new ArrayList<>(Arrays.asList(new User("John", "Doe", "johndoe@gmail.com", "12345678", "Test Street", "Test City", "1234")));

        addBooking(d, l, p, r, u);
    }
    
    public static void main(String[] args) {
        FirebaseConnection firebaseConnection = new FirebaseConnection();
        firebaseConnection.addBookingTest();
    }
}
