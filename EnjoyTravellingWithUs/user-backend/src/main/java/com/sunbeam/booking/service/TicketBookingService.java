package com.sunbeam.booking.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketBookingService {
    private int availableSeats = 10;
    private final ReentrantLock lock = new ReentrantLock();

    public void bookSeat(String userName) {
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(userName + " successfully booked a seat.");
                availableSeats--;
                System.out.println("Seats left: " + availableSeats);
            } else {
                System.out.println("Sorry, " + userName + ". No seats available.");
            }
        } finally {
            lock.unlock();
        }
    }
}
