package com.ticketbooking.bookingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.bookingservice.client.InventoryServiceClient;
import com.ticketbooking.bookingservice.entity.Customer;
import com.ticketbooking.bookingservice.repository.CustomerRepository;
import com.ticketbooking.bookingservice.request.BookingRequest;
import com.ticketbooking.bookingservice.response.BookingResponse;
import com.ticketbooking.bookingservice.response.InventoryResponse;

@Service
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public BookingService(final CustomerRepository customerRepository, final InventoryServiceClient inventoryServiceClient){
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }
    public BookingResponse createBooking(final BookingRequest request){
         // check if user exists
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);
        if (customer == null) {
            throw new RuntimeException("User not found");
        }
        // check if there is enough inventory
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        
        if (inventoryResponse.getCapacity() < request.getTicketCount()) {
            throw new RuntimeException("Not enough inventory");
        }
        
        return BookingResponse.builder().build();
    }
}
