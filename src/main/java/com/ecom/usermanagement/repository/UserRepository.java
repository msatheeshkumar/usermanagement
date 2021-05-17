package com.ecom.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import com.ecom.usermanagement.entity.UserProfile;

public interface UserRepository {
	/**
     * Returns the produce with the specified id.
     *
     * @param id        ID of the user to retrieve.
     * @return          The requested user if found.
     */
    Optional<UserProfile> findById(Long id);

    /**
     * Returns all users in the database.
     *
     * @return          All users in the database.
     */
    List<UserProfile> findAll();

    /**
     * Updates the specified user, identified by its id.
     *
     * @param user   The user to update.
     * @return          True if the update succeeded, otherwise false.
     */
    boolean update(UserProfile user);

    /**
     * Saves the specified user to the database.
     *
     * @param user   The user to save to the database.
     * @return          The saved user.
     */
    UserProfile save(UserProfile user);

    /**
     * Deletes the user with the specified id.
     * @param id        The id of the user to delete.
     * @return          True if the operation was successful.
     */
    boolean delete(Long id);
}
