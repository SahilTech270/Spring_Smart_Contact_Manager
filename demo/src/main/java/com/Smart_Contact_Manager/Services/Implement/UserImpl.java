package com.Smart_Contact_Manager.Services.Implement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Smart_Contact_Manager.Entity.UserEntity;
import com.Smart_Contact_Manager.Repository.UserRepo;
import com.Smart_Contact_Manager.Services.UserService;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity saveUser(UserEntity user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }

    @Override
    public Optional<UserEntity> updateUser(UserEntity user) {
        UserEntity userUpdate = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        userUpdate.setName(user.getName());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setAbout(user.getAbout());
        userUpdate.setPhoneNumber(user.getPhoneNumber());
        userUpdate.setProfilePic(user.getProfilePic());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setActive(user.isActive());
        userUpdate.setEmailVerified(user.isEmailVerified());
        userUpdate.setPhoneVerified(user.isPhoneVerified());
        userUpdate.setProfilePic(user.getProfilePic());

        UserEntity save = userRepo.save(userUpdate);
        return Optional.of(save);

    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userDelete = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepo.delete(userDelete);
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

}
