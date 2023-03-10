package com.example.em.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "event_booking",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"event_id", "user_id"})})
//ràng buộc 1 user chỉ dc book 1 lần cho 1 sự kiện
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "time_booking")
    private LocalDateTime timeBooking;
    @Column(name = "status")
    private Boolean status;

//public void validateBooking() {
//        if (event.getBookings().stream()
//                .anyMatch(booking -> booking.getUser().getId().equals(user.getId()))) {
//            throw new IllegalStateException("User already booked for this event");
//        }
//    }
}
