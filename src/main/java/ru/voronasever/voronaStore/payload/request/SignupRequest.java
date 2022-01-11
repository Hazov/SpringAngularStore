package ru.voronasever.voronaStore.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {

    @NotBlank
    @Email
    @Size(min = 5, max = 40)
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String pass;

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Size(max = 15)
    private String role;
}
