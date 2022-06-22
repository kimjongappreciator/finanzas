package com.upc.finanzas.service.impls;

import com.upc.finanzas.entity.Inflation;
import com.upc.finanzas.exception.ResourceNotFoundException;
import com.upc.finanzas.repository.InflationRepository;
import com.upc.finanzas.service.InflationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InflationServiceImpl implements InflationService {
    @Autowired
    private InflationRepository inflationRepository;

    @Transactional
    @Override
    public Inflation save(Inflation entity) throws Exception {
        return inflationRepository.save(entity);
    }
    @Transactional
    @Override
    public List<Inflation> findAll() throws Exception {
        return inflationRepository.findAll();
    }
    @Transactional
    @Override
    public Inflation findById(Long aLong) throws Exception {
        return ((inflationRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("inflation", "Id", aLong))));
    }
    @Transactional
    @Override
    public Inflation update(Inflation entity, Long id) throws Exception {
        entity.setId(id);
        return inflationRepository.save(entity);
    }
    @Transactional
    @Override
    public void deleteById(Long aLong) throws Exception {
        inflationRepository.deleteById(aLong);
    }
    @Override
    public List<Inflation> getByBonoId(Long aLong) throws Exception{
        return inflationRepository.getByBonoId(aLong);
    }

}
