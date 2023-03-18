package com.example.em.dto.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CEventDTO {
    private String name;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String desc;
    private String stringImage;
    private Long managerId;


}
