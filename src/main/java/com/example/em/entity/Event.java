package com.example.em.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    @Column(name = "name")
    private String name;
    private String location;
    //@Column(name = "file_path")
    //private String filePath;
//    @Lob
//    private byte[] image;
    @Lob
    @Column
    private String stringImage;

    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "description")
    private String desc;
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventBooking> bookings;
}

