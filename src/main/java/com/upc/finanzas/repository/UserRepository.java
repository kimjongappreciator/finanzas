package com.upc.finanzas.repository;

import com.upc.finanzas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.Email = ?1")
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
