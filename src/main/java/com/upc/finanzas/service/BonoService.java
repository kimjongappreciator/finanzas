package com.upc.finanzas.service;

import com.upc.finanzas.entity.Bono;

import java.util.List;

public interface BonoService extends CrudService<Bono, Long>{
    List<Bono> getByUserId(Long along) throws Exception;
}
