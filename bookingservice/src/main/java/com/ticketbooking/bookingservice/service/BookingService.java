package com.ticketbooking.bookingservice.service;

import org.springframework.stereotype.Service;

import com.ticketbooking.bookingservice.request.BookingRequest;
import com.ticketbooking.bookingservice.response.BookingResponse;

@Service
public class BookingService {
    public BookingResponse createBooking(final BookingRequest request){
        return BookingResponse.builder().build();
    }
}
