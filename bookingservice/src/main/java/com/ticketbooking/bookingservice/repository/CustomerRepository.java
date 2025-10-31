package com.ticketbooking.bookingservice.repository;

import com.ticketbooking.bookingservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}