package com.example.em.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DEventDTO {
    private String name;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String desc;
    private String file_path;


}
