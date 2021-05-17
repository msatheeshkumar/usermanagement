package com.ecom.usermanagement.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.usermanagement.entity.UserProfile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This controller is responsible for user creation, updation and deletion.
 * @author satheeshkumar
 *
 */
@RequestMapping("/api/v1")
public interface UserManagementController {

	/**
	 * Returns the user with the specified ID.
	 *
	 * @param id    The ID of the user to retrieve.
	 * @return      The user with the specified ID.
	 */
	@ApiOperation(value = "Get the user details for the giver user id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retrieved user details successfully", response=UserProfile.class),
			@ApiResponse(code = 404, message = "Not Found", response=UserProfile.class), 
			@ApiResponse(code = 500, message = "Failure", response=UserProfile.class)})
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@ApiParam(value = "User Id for Users") @PathVariable Long id);
    /**
     * Creates a new user.
     * @param user   The user to create.
     * @return          The user.
     */
	@ApiOperation(value = "create the user details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "saved user details successfully", response=UserProfile.class),
			@ApiResponse(code = 400, message = "Bad request payload", response=UserProfile.class), 
			@ApiResponse(code = 500, message = "Failure", response=UserProfile.class)})
    @PostMapping(value = "/user/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfile> createUser(@ApiParam(value = "User profile request payload for user creation") @RequestBody UserProfile user);
    
    /**
     * Find the User for id and updates the user information
     * @param user The use to update
     * @param id 
     * @return User info
     */
	@ApiOperation(value = "update the existing user details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "updated user details successfully", response=UserProfile.class),
			@ApiResponse(code = 400, message = "Bad request payload", response=UserProfile.class), 
			@ApiResponse(code = 404, message = "Not Found", response=UserProfile.class),
			@ApiResponse(code = 500, message = "Failure", response=UserProfile.class)})
    @PutMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@ApiParam(value = "User profile request payload for user updation") @RequestBody UserProfile user,
    		@ApiParam(value = "User id of existing user") @PathVariable Long id);

    /**
     * Delete the user for the matching user id
     * @param id The id needs to be found and delete it
     * @return OK - deleted or not
     */
	@ApiOperation(value = "delete the existing user details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deleted user details successfully", response=UserProfile.class),
			@ApiResponse(code = 404, message = "Not Found", response=UserProfile.class),
			@ApiResponse(code = 500, message = "Failure", response=UserProfile.class)})
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@ApiParam(value = "User id of existing user") @PathVariable Long id);

}
