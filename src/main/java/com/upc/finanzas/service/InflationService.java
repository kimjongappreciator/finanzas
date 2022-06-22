package com.upc.finanzas.service;

import com.upc.finanzas.entity.Inflation;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface InflationService extends CrudService<Inflation, Long> {
    List<Inflation> getByBonoId(Long aLong) throws Exception;
}
