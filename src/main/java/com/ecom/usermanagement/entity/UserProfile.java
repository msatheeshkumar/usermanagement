package com.ecom.usermanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user_profile")
/**
 * Entity class
 * @author satheeshkumar
 *
 */
public class UserProfile implements Serializable{

	private static final long serialVersionUID = 2763339336261508184L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private Long id;
    @Column(name="username", nullable = false, updatable = false)
    private String username;
    @Column(name="password", nullable = false, updatable = true)
    private String password;
    @Column(name="first_name", nullable = false, updatable = false)
    private String firstName;
    @Column(name="last_name", nullable = false, updatable = false)
    private String lastName;

    @Column(name="email", nullable = false, updatable = true)
    private String email;
    @Column(name="phone", nullable = false, updatable = true)
    private String phone;
    @Column(name="active", nullable = false, updatable = false)
    private boolean enabled=true;
	
}
