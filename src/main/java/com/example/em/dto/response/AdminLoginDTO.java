package com.example.em.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginDTO {
    private Long id;
    private final String role = "AD";
    private String username;
}
