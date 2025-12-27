package com.Smart_Contact_Manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Smart_Contact_Manager.Entity.ContactEntity;

@Repository
public interface ContactRepo extends JpaRepository<ContactEntity, String> {


}
