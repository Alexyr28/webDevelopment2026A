package com.epw.activities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCustomerRequest {

    @NotBlank(message = "fullName is required")
    @Size(max = 150, message = "fullName must be <= 150 chars")
    private String fullName;

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    @Size(max = 200, message = "email must be <= 200 chars")
    private String email;

    @Size(max = 30, message = "phone must be <= 30 chars")
    private String phone;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
