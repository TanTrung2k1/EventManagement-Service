package com.example.em.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    private Long id;
    private final String role = "US";
    private String email;
    private String name;
    private String address;
    private Boolean status;
}
