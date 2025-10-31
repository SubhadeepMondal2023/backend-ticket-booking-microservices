package com.example.ticketbooking.inventoryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketbooking.inventoryservice.entity.Event;
import com.example.ticketbooking.inventoryservice.entity.Venue;
import com.example.ticketbooking.inventoryservice.repository.EventRepository;
import com.example.ticketbooking.inventoryservice.repository.VenueRepository;
import com.example.ticketbooking.inventoryservice.response.EventInventoryResponse;
import com.example.ticketbooking.inventoryservice.response.VenueInventoryResponse;

@Service
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    public List<EventInventoryResponse> getAllEvents() {
        final List<Event> events = eventRepository.findAll();

        return events.stream().map(event -> EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .build()).collect(Collectors.toList());
    }  

    public VenueInventoryResponse getVenueInformation(final Long venueId) {
        final Venue venue = venueRepository.findById(venueId).orElse(null);

        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }

     public EventInventoryResponse getEventInventory(final Long eventId) {
        final Event event = eventRepository.findById(eventId).orElse(null);

        return EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .eventId(event.getId())
                .build();
    }
}
