package com.furkankayam.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserRequestResponseDto(
        @Schema(
                description = "User First Name",
                name = "firstName",
                type = "String",
                example = "john")
        String firstName,

        @Schema(
                description = "User List Name",
                name = "lastName",
                type = "String",
                example = "dale")
        String lastName,

        @Schema(
                description = "User Username",
                name = "username",
                type = "String",
                example = "johndale10")
        String username,

        @Schema(
                description = "User Email",
                name = "email",
                type = "String",
                example = "john@gmail.com")
        String email
) {
}
