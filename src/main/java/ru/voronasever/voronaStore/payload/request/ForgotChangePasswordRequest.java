package ru.voronasever.voronaStore.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForgotChangePasswordRequest
{
    private String eenc;
    private String key;
    private String pass;
}
