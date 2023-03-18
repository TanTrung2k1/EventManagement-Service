package com.example.em.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
}
