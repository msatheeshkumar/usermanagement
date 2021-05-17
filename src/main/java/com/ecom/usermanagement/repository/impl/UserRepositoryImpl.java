package com.ecom.usermanagement.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ecom.usermanagement.entity.UserProfile;
import com.ecom.usermanagement.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Repository class to persist data in DB
 * @author satheeshkumar
 *
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

        // Build a SimpleJdbcInsert object from the specified data source
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("user_profile")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<UserProfile> findById(Long id) {
        try {
        	UserProfile userProfile = jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE id = ?",
                    new Object[]{id},
                    (rs, rowNum) -> {
                        UserProfile user = new UserProfile();
                        user.setId(rs.getLong("id"));
                        user.setFirstName(rs.getString("first_name"));
                        user.setLastName(rs.getString("last_name"));
                        user.setEmail(rs.getString("email"));
                        user.setPhone(rs.getString("phone"));
                        user.setPassword(rs.getString("password"));
                        user.setUsername(rs.getString("username"));
                        return user;
                    });
            return Optional.of(userProfile);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<UserProfile> findAll() {
        return jdbcTemplate.query("SELECT * FROM user_profile",
                (rs, rowNumber) -> {
                    UserProfile user = new UserProfile();
                    user.setId(rs.getLong("id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    return user;
                });
    }

    @Override
    public boolean update(UserProfile user) {
        return jdbcTemplate.update("UPDATE user_profile SET email = ?, phone = ?, password = ? WHERE id = ?",
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                user.getId()) == 1;
    }

    @Override
    public UserProfile save(UserProfile user) {
        // Build the users parameters we want to save
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("username", user.getUsername());
        parameters.put("password", user.getPassword());
        parameters.put("first_name", user.getFirstName());
        parameters.put("last_name", user.getLastName());
        parameters.put("email", user.getEmail());
        parameters.put("phone", user.getPhone());
        parameters.put("active", true);

        // Execute the query and get the generated key
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);

        log.info("Inserting user into database, generated key is: {}", newId);

        // Update the user's ID with the new key
        user.setId((Long)newId);

        // Return the complete user
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update("DELETE FROM user_profile WHERE id = ?", id) == 1;
    }

}
