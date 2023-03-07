package com.example.em.repository;

import com.example.em.entity.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventBookingRepository extends JpaRepository<EventBooking, Long> {
}
