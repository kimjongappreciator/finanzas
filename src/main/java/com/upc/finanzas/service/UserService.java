package com.upc.finanzas.service;

import com.upc.finanzas.entity.User;
import com.upc.finanzas.security.domain.service.communication.AuthenticateRequest;
import com.upc.finanzas.service.communication.RegisterUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAll();
    Page<User> getAll(Pageable pageable);
    ResponseEntity<?> authenticate(AuthenticateRequest request);
    ResponseEntity<?> register(RegisterUserRequest request);
    User getById(Long directorId);
    User create(User director);
    User update(User director,Long directorId);
    ResponseEntity<?> delete(Long directorId);
}
