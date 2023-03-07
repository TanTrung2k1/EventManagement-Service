package com.example.em.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private Boolean status;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Event> events;
}
