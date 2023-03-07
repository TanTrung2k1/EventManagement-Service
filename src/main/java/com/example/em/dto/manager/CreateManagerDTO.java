package com.example.em.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateManagerDTO {
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
}