package com.ecom.usermanagement.controller.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.usermanagement.configuration.SwaggerConfiguration.UserManagementApiGroup;
import com.ecom.usermanagement.controller.UserManagementController;
import com.ecom.usermanagement.entity.UserProfile;
import com.ecom.usermanagement.service.UserManagementService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * This controller is responsible for user creation, updation and deletion.
 * @author satheeshkumar
 *
 */
@Slf4j
@RestController
@Api(value = "user management requestS", tags = "user management service", description = "Operation pertaining to User management service")
@UserManagementApiGroup
public class UserManagementControllerImpl implements UserManagementController{

	@Autowired
	private UserManagementService userManagementService;

	/**
	 * Returns the user with the specified ID.
	 *
	 * @param id    The ID of the user to retrieve.
	 * @return      The user with the specified ID.
	 */
	public ResponseEntity<?> getUser(Long id) {

		return userManagementService.findById(id)
				.map(user -> {
					try {
						return ResponseEntity
								.ok()
								.location(new URI("/user/" + user.getId()))
								.body(user);
					} catch (URISyntaxException e ) {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					}
				})
				.orElse(ResponseEntity.notFound().build());
	}
    /**
     * Creates a new user.
     * @param user   The user to create.
     * @return          The user.
     */
    public ResponseEntity<UserProfile> createUser(UserProfile user) {
        log.info("Creating new user with name: {}, email: {}", user.getUsername(), user.getEmail());

        // Create the new user
        UserProfile newUser = userManagementService.save(user);

        try {
            // Build a created response
            return ResponseEntity
                    .created(new URI("/user/" + newUser.getId()))
                    .body(newUser);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Find the User for id and updates the user information
     * @param user The use to update
     * @param id 
     * @return User info
     */
    public ResponseEntity<?> updateUser(@RequestBody UserProfile user,
                                           @PathVariable Long id) {
        // Get the existing user
        Optional<UserProfile> existingUser = userManagementService.findById(id);
        
        log.info("Updating user with id: {}", id);

        return existingUser.map(exUser -> {
            // Update the user
            exUser.setPassword(user.getPassword());
            exUser.setEmail(user.getEmail());
            exUser.setPhone(user.getPhone());

            log.info("Updating usr with ID: " + user.getId()
                    + ", email=" + user.getEmail()
                    + ", phone=" + user.getPhone());

            try {
                // Update the user and return an ok response
                if (userManagementService.update(exUser)) {
                    return ResponseEntity.ok()
                            .location(new URI("/user/" + exUser.getId()))
                            .body(exUser);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (URISyntaxException e) {
                // An error occurred trying to create the location URI, return an error
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete the user for the matching user id
     * @param id The id needs to be found and delete it
     * @return OK - deleted or not
     */
    public ResponseEntity<?> deleteUser(Long id) {

        log.info("Deleting user with ID {}", id);

        // Get the existing user
        Optional<UserProfile> existingUser = userManagementService.findById(id);
        
        return existingUser.map(p -> {
            if (userManagementService.delete(p.getId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

}
