package com.ticketbooking.bookingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbooking.bookingservice.request.BookingRequest;
import com.ticketbooking.bookingservice.response.BookingResponse;
import com.ticketbooking.bookingservice.service.BookingService;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public BookingResponse createBooking(@RequestBody BookingRequest request){
        return bookingService.createBooking(request);
    }
}
