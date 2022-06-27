package com.upc.finanzas.service.impls;

import com.upc.finanzas.entity.User;
import com.upc.finanzas.exception.ResourceNotFoundException;
import com.upc.finanzas.middleware.JwtHandlerUser;
import com.upc.finanzas.middleware.UserDetailsImpl;
import com.upc.finanzas.repository.UserRepository;
import com.upc.finanzas.resource.AuthenticateUserResource;
import com.upc.finanzas.security.domain.model.entity.Role;
import com.upc.finanzas.security.domain.model.enumeration.Roles;
import com.upc.finanzas.security.domain.persistence.RoleRepository;
import com.upc.finanzas.security.domain.service.communication.AuthenticateRequest;
import com.upc.finanzas.service.UserService;
import com.upc.finanzas.service.communication.AuthenticateUserResponse;
import com.upc.finanzas.service.communication.RegisterUserRequest;
import com.upc.finanzas.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String ENTITY = "Director";
    @Autowired
    private UserRepository directorRepository;
    @Autowired
    private Validator validator;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandlerUser handler;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EnhancedModelMapper mapper;

    /*public DirectorServiceImpl(DirectorRepository directorRepository, Validator validator) {
        this.directorRepository = directorRepository;
        this.validator = validator;
    }*/

    @Override
    public List<User> getAll() {
        return directorRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return directorRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = handler.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateUserResource resource = mapper.map(userDetails, AuthenticateUserResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticateUserResponse response = new AuthenticateUserResponse(resource);

            return ResponseEntity.ok(response.getResource());


        } catch (Exception e) {
            AuthenticateUserResponse response = new AuthenticateUserResponse(String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> register(RegisterUserRequest request) {
        if (directorRepository.existsByEmail(request.getEmail())) {
            AuthenticateUserResponse response = new AuthenticateUserResponse("Email is already used.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        try {

            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if (rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_USER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            logger.info("Roles: {}", roles);

            User user = new User()
                    .setName(request.getFirst_name())
                    .setEmail(request.getEmail())
                    .setPassword(encoder.encode(request.getPassword()));


            directorRepository.save(user);
            DirectorResource resource = mapper.map(user, DirectorResource.class);
            RegisterDirectorResponse response = new RegisterDirectorResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {

            RegisterDirectorResponse response = new RegisterDirectorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());

        }

    }

    @Override
    public Director getById(Long directorId) {
        return directorRepository.findById(directorId)
                .orElseThrow( () -> new ResourceNotFoundException(ENTITY, directorId));
    }

    @Override
    public Director create(Director director) {
        Set<ConstraintViolation<Director>> violations = validator.validate(director);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingEmail = directorRepository.findByEmail(director.getEmail());
        if(existingEmail != null) {
            throw new ResourceValidationException("email is already taken");
        }

        return directorRepository.save(director);
    }

    @Override
    public Director update(Long directorId, Director director) {
        Set<ConstraintViolation<Director>> violations = validator.validate(director);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return directorRepository.findById(directorId).map( data ->
                directorRepository.save(
                        data.withFirst_name(director.getFirst_name())
                                .withLast_name(director.getLast_name())
                                .withAge(director.getAge())
                                .withEmail(director.getEmail())
                                .withPhone(director.getPhone()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, directorId));
    }

    @Override
    public ResponseEntity<?> delete(Long directorId) {
        return directorRepository.findById(directorId).map(data -> {
            directorRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, directorId));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Director user = directorRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return DirectorDetailsImpl.build(user);
    }
}
