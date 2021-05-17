package com.ecom.usermanagement.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ecom.usermanagement.entity.UserProfile;
import com.ecom.usermanagement.service.impl.UserManagementServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserManagementControllerTest {
	
	@MockBean
	private UserManagementServiceImpl userManagementServiceImpl;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET /api/v1/user/{id} SUCCESS")
	void testGetUser() throws Exception {
		UserProfile userProfile = new UserProfile();
		userProfile.setId(1L);
		userProfile.setUsername("testuser");
		userProfile.setPassword("pass");
		userProfile.setFirstName("test");
		userProfile.setLastName("test");
		Optional<UserProfile> userProfileOpt = Optional.ofNullable(userProfile);
		
		doReturn(userProfileOpt).when(userManagementServiceImpl).findById(1L);
		
		mockMvc.perform(get("/api/v1/user/1"))
				.andExpect(jsonPath("$.firstName", is("test")))
				.andExpect(jsonPath("$.username", is("testuser")));
		
	}
	
	@Test
	@DisplayName("GET /api/v1/user/{id} NOT FOUNT")
	void testGetUser_NotFound() throws Exception {
		
		doReturn(Optional.empty()).when(userManagementServiceImpl).findById(1L);
		
		mockMvc.perform(get("/api/v1/user/1"))
				.andExpect(status().isNotFound());
		
	}

	@Test
	@DisplayName("POST /api/v1/user/create SUCCESS")
	void testCreateUser() throws Exception {
		
		UserProfile userProfile = new UserProfile();
		userProfile.setUsername("testuser");
		userProfile.setPassword("pass");
		userProfile.setFirstName("test");
		userProfile.setLastName("test");
		
		UserProfile newUserProfile = new UserProfile();
		newUserProfile.setId(1L);
		newUserProfile.setUsername("testuser");
		newUserProfile.setPassword("pass");
		newUserProfile.setFirstName("test");
		newUserProfile.setLastName("test");
		
		doReturn(newUserProfile).when(userManagementServiceImpl).save(eq(userProfile));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "testuser");
		jsonObject.put("password", "pass");
		jsonObject.put("firstName", "test");
		jsonObject.put("lastName", "test");
		
		mockMvc.perform(post("/api/v1/user/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject.toString()))
		
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is("test")))
				.andExpect(jsonPath("$.username", is("testuser")));
	}

	@Test
	@DisplayName("PUT /api/v1/user/{id} SUCCESS")
	void testUpdateUser() throws Exception {
		UserProfile userProfile = new UserProfile();
		userProfile.setId(1L);
		userProfile.setUsername("testuser");
		userProfile.setPassword("pass");
		userProfile.setFirstName("test");
		userProfile.setLastName("test");
		Optional<UserProfile> userProfileOpt = Optional.ofNullable(userProfile);
		
		UserProfile newUserProfile = new UserProfile();
		newUserProfile.setId(1L);
		newUserProfile.setUsername("testuser");
		newUserProfile.setPassword("pass1");
		newUserProfile.setFirstName("test2");
		newUserProfile.setLastName("test");
		
		doReturn(userProfileOpt).when(userManagementServiceImpl).findById(1L);
		
		doReturn(true).when(userManagementServiceImpl).update(eq(userProfile));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "testuser");
		jsonObject.put("password", "pass1");
		jsonObject.put("firstName", "test2");
		jsonObject.put("lastName", "test");
		
		mockMvc.perform(put("/api/v1/user/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject.toString()))
		
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.password", is("pass1")));
	}
	
	@Test
	@DisplayName("PUT /api/v1/user/{id} NOT FOUND")
	void testUpdateUser_NotFound() throws Exception {
		UserProfile userProfile = new UserProfile();
		userProfile.setId(1L);
		userProfile.setUsername("testuser");
		userProfile.setPassword("pass");
		userProfile.setFirstName("test");
		userProfile.setLastName("test");
		Optional<UserProfile> userProfileOpt = Optional.ofNullable(userProfile);
		
		UserProfile newUserProfile = new UserProfile();
		newUserProfile.setId(1L);
		newUserProfile.setUsername("testuser");
		newUserProfile.setPassword("pass1");
		newUserProfile.setFirstName("test2");
		newUserProfile.setLastName("test");
		
		doReturn(userProfileOpt).when(userManagementServiceImpl).findById(1L);
		
		doReturn(false).when(userManagementServiceImpl).update(eq(userProfile));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "testuser");
		jsonObject.put("password", "pass1");
		jsonObject.put("firstName", "test2");
		jsonObject.put("lastName", "test");
		
		mockMvc.perform(put("/api/v1/user/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject.toString()))
		
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("DELETE /api/v1/user/{id} SUCCESS")
	void testDeleteUser() throws Exception {
		UserProfile userProfile = new UserProfile();
		userProfile.setId(1L);
		userProfile.setUsername("testuser");
		userProfile.setPassword("pass");
		userProfile.setFirstName("test");
		userProfile.setLastName("test");
		Optional<UserProfile> userProfileOpt = Optional.ofNullable(userProfile);
		
		doReturn(userProfileOpt).when(userManagementServiceImpl).findById(1L);
		
		doReturn(true).when(userManagementServiceImpl).delete(1L);
		
		mockMvc.perform(delete("/api/v1/user/1"))
				.andExpect(status().isOk());
	}

}
