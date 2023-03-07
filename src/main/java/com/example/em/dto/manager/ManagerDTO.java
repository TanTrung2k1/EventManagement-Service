package com.example.em.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private boolean status;
}
