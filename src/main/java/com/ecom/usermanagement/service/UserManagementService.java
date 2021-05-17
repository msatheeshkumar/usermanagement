package com.ecom.usermanagement.service;

import java.util.List;
import java.util.Optional;

import com.ecom.usermanagement.entity.UserProfile;

public interface UserManagementService {
	
    /**
     * Returns the UserProfile with the specified id.
     *
     * @param id        ID of the UserProfile to retrieve.
     * @return          The requested UserProfile if found.
     */
    Optional<UserProfile> findById(Long id);

    /**
     * Returns all UserProfiles in the database.
     *
     * @return          All UserProfiles in the database.
     */
    List<UserProfile> findAll();

    /**
     * Updates the specified UserProfile, identified by its id.
     *
     * @param UserProfile   The UserProfile to update.
     * @return          True if the update succeeded, otherwise false.
     */
    boolean update(UserProfile UserProfile);

    /**
     * Saves the specified UserProfile to the database.
     *
     * @param UserProfile   The UserProfile to save to the database.
     * @return          The saved UserProfile.
     */
    UserProfile save(UserProfile UserProfile);

    /**
     * Deletes the UserProfile with the specified id.
     * @param id        The id of the UserProfile to delete.
     * @return          True if the operation was successful.
     */
    boolean delete(Long id);

}
