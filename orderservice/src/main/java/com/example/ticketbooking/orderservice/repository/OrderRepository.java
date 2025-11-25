package com.example.ticketbooking.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketbooking.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
