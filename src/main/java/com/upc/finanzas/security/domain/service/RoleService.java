package com.upc.finanzas.security.domain.service;

import com.upc.finanzas.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {
    void seed();
    List<Role> getAll();
}
