package com.Smart_Contact_Manager.Services;

import java.util.List;

import com.Smart_Contact_Manager.Entity.ContactEntity;

public interface ContactService {

    //Save Contact
    ContactEntity saveContact(ContactEntity contactEntity);

    //Update Contact
    ContactEntity updateContact(ContactEntity contactEntity);

    //Get All Contact
    List<ContactEntity> getAllContact();

    //Get By Id
    ContactEntity getContactById(String contactId);

    //Delete Contact
    void deleteContact(String contactId);

}
