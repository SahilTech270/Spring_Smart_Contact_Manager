package com.Smart_Contact_Manager.Services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;

public interface UserService {

    User saveUser(User user);

    Optional <User> getUserByEmail(String email);

    Optional <User> updateUser(User user);

    void deleteUser(String userId);

    boolean isUserExistsByEmail(String email);

    List<User> getAllUsers();

}
