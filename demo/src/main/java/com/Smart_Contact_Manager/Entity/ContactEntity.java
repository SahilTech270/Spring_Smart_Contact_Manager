package com.Smart_Contact_Manager.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ContactEntity {

    @Id
    private String contactId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String address;

    @Column(nullable = false, length = 10)
    private String phone;

    private String description;
    
    private boolean favorite = false;

    private String linkedIn;
    private String website;

    @ManyToOne
    private UserEntity userEntity;
}
