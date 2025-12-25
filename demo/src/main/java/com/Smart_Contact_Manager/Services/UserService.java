package com.Smart_Contact_Manager.Services;

import java.util.List;
import java.util.Optional;

import com.Smart_Contact_Manager.Entity.UserEntity;

public interface UserService {

    UserEntity saveUser(UserEntity user);

    UserEntity getUserByEmail(String email);

    Optional <UserEntity> updateUser(UserEntity user);

    void deleteUser(String userId);

    boolean isUserExistsByEmail(String email);

    List<UserEntity> getAllUsers();

}
 