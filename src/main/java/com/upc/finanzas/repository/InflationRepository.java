package com.upc.finanzas.repository;

import com.upc.finanzas.entity.Inflation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InflationRepository extends JpaRepository<Inflation, Long> {

    List<Inflation> getByBonoId(Long aLong) throws Exception;
}
