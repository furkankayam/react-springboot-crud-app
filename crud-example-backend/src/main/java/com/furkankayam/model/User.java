package com.furkankayam.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "User ID",
            name = "id",
            type = "Long",
            example = "3")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Schema(
            description = "User First Name",
            name = "firstName",
            type = "String",
            example = "john")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Schema(
            description = "User List Name",
            name = "lastName",
            type = "String",
            example = "dale")
    private String lastName;

    @Column(unique = true, nullable = false)
    @Schema(
            description = "User Username",
            name = "username",
            type = "String",
            example = "johndale10")
    private String username;

    @Column(unique = true, nullable = false)
    @Schema(
            description = "User Email",
            name = "email",
            type = "String",
            example = "john@gmail.com")
    private String email;

    //Constructions
    public User() {
    }

    public User(Long id, String firstName, String lastName, String username, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }

    //Getter-Setter

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
