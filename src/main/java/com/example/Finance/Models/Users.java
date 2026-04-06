package com.example.Finance.Models;


import com.example.Finance.Models.enums.UserRole;
import com.example.Finance.Models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userId;

    @NotBlank(message = "name cannot be empty")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "email cannot be empty")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole =  UserRole.VIEWER;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
