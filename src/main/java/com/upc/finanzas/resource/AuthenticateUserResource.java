package com.upc.finanzas.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateUserResource {
    private Long id;
    private String name;
    private String email;
    private List<String> roles;
    private String token;
}
