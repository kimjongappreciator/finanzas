package com.upc.finanzas.service.impls;

import com.upc.finanzas.entity.Bono;
import com.upc.finanzas.entity.Inflation;
import com.upc.finanzas.exception.ResourceNotFoundException;
import com.upc.finanzas.repository.BonoRepository;
import com.upc.finanzas.service.BonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BonoServiceImpl implements BonoService {
    @Autowired
    private BonoRepository bonoRepository;

    @Transactional
    @Override
    public Bono save(Bono entity) throws Exception {
        return bonoRepository.save(entity);
    }
    @Transactional
    @Override
    public List<Bono> findAll() throws Exception {
        return bonoRepository.findAll();
    }
    @Transactional
    @Override
    public Bono findById(Long aLong) throws Exception {
        return ((bonoRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("period", "Id", aLong))));
    }
    @Transactional
    @Override
    public Bono update(Bono entity, Long id) throws Exception {
        //return bonoRepository.save(entity);
        entity.setId(id);
        return this.bonoRepository.save(entity );
    }
    @Transactional
    @Override
    public void deleteById(Long aLong) throws Exception {
        bonoRepository.deleteById(aLong);
    }

    @Override
    public List<Bono> getByUserId(Long aLong) throws Exception{
        return bonoRepository.getByUserId(aLong);
    }


}
