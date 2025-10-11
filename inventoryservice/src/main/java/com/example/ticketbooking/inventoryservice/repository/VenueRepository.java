package com.example.ticketbooking.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ticketbooking.inventoryservice.entity.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long>{

}
