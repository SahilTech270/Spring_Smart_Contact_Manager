package com.Smart_Contact_Manager.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor          
@AllArgsConstructor

public class UserEntity {

    @Id()
    private String userId;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 500)
    private String about;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    private String profilePic;

    private boolean isActive = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    private String provider = "SELF";
    private String providerId;

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    private List<ContactEntity> contacts = new ArrayList<>();


}
