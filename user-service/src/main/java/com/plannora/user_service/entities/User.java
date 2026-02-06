package com.plannora.user_service.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_users_email", columnList = "email"),
                @Index(name = "idx_users_phone", columnList = "phone")
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private  String name;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private  String email;

    @Column(name = "password", nullable = false)
    private  String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private  Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private  Status status;

    // OPTIONAL — not required for auth
    @Column(name = "phone", unique = true, nullable = true, length = 15)
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    public User(String name,
                String email,
                String password,
                Role role,
                Status status,
                String phone) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.phone = phone;
    }
}