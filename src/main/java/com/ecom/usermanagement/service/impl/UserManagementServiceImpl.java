package com.ecom.usermanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.usermanagement.entity.UserProfile;
import com.ecom.usermanagement.repository.UserRepository;
import com.ecom.usermanagement.service.UserManagementService;

import lombok.extern.slf4j.Slf4j;
/**
 * Service class to invoke repository.
 * @author satheeshkumar
 *
 */
@Service
@Slf4j
public class UserManagementServiceImpl implements UserManagementService {

	private final UserRepository userRepository;
	
    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find the user by id
     */
    @Override
    public Optional<UserProfile> findById(Long id) {
        log.info("Find product with id: {}", id);
        return userRepository.findById(id);
    }

    /**
     * Find all user registered
     */
    @Override
    public List<UserProfile> findAll() {
        log.info("Find all products");
        return userRepository.findAll();
    }

    /**
     * Update the user profile
     */
    @Override
    public boolean update(UserProfile user) {
        log.info("Update product: {}", user);
        return userRepository.update(user);
    }

    /**
     * Save the user details
     */
    @Override
    public UserProfile save(UserProfile user) {
        log.info("Save product to the database: {}", user);
        return userRepository.save(user);
    }

    /**
     * Delete the user profile.
     */
    @Override
    public boolean delete(Long id) {
        log.info("Delete product with id: {}", id);
        return userRepository.delete(id);
    }
	
	

}
