package com.upc.finanzas.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResource {

    private Long id;
    private String name;
    private String email;
    private List<String> roles;
}
