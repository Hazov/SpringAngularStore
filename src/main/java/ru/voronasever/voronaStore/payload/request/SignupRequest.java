package ru.voronasever.voronaStore.payload.request;

import jdk.jfr.DataAmount;
import lombok.Data;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.Feedback;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 6, max = 40)
    private String pass;

    @NotBlank
    @Size(min = 5, max = 40)
    private String email;

    @Size(max = 15)
    private String role;
}
