package com.serviceharbor.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNo;
    private String role; // This could be an enum or a string, depending on your implementation

}
