package com.energizeglobal.com.internship.angular.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 25)
    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 25)
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    //    @Past
    private LocalDate birthday;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
