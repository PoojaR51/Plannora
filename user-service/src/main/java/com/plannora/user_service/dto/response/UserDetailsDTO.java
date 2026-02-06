package com.plannora.user_service.dto.response;

import com.plannora.user_service.entities.Role;
import com.plannora.user_service.entities.Status;
import lombok.Data;

@Data
public class UserDetailsDTO {
    private Long id;

    private String name;


    private String email;


    private Role role;


    private Status status;

    private String phone;
}
