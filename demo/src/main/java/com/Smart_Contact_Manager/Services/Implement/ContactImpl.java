package com.Smart_Contact_Manager.Services.Implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Smart_Contact_Manager.Entity.ContactEntity;
import com.Smart_Contact_Manager.Repository.ContactRepo;
import com.Smart_Contact_Manager.Services.ContactService;

@Service
public class ContactImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public ContactEntity saveContact(ContactEntity contactEntity) {
        String contactId = UUID.randomUUID().toString();
        contactEntity.setContactId(contactId);
        return contactRepo.save(contactEntity);
    }

    @Override
    public ContactEntity updateContact(ContactEntity contactEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public List<ContactEntity> getAllContact() {
        return contactRepo.findAll();
    }

    @Override
    public ContactEntity getContactById(String contactId) {
        return contactRepo.findById(contactId).orElseThrow(() -> new RuntimeException("Contact Not Found"));
    }

    @Override
    public void deleteContact(String contactId) {
        contactRepo.deleteById(contactId);
    }

}
