package com.prathamesh.finpulse.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor                 // Creates a no-argument constructor.
@AllArgsConstructor                // Creates a constructor with all fields.
@Builder                           // Allows you to create objects using the Builder pattern.
@Entity
@Table(name = "users")
public class User extends  BaseEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
