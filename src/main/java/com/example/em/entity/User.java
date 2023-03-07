package com.example.em.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
    private Boolean status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EventBooking> bookings;
}