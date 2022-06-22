package com.upc.finanzas.repository;

import com.upc.finanzas.entity.Bono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonoRepository extends JpaRepository<Bono, Long> {
    List<Bono> getByUserId(Long aLong) throws Exception;
}
