package com.example.authservice.domain.model;

import com.example.authservice.domain.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Role role;

}
