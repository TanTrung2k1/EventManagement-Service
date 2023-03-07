package com.example.em.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerLoginDTO {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;

}
