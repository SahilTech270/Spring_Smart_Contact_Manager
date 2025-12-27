package com.Smart_Contact_Manager.Form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ContactForm {
    
    public String name;
    public String email;
    public String phone;
    public String address;
    public String description;
    public String linkedin;
    public boolean favorite;
    public MultipartFile picture;
    public String website;

}
