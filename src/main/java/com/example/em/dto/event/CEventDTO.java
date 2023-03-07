package com.example.em.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CEventDTO {
    private String name;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String desc;

}
