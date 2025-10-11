package com.example.ticketbooking.inventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketbooking.inventoryservice.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
