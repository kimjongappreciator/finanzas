package com.upc.finanzas.service.impls;

import com.upc.finanzas.entity.Inflation;
import com.upc.finanzas.entity.Period;
import com.upc.finanzas.exception.ResourceNotFoundException;
import com.upc.finanzas.repository.InflationRepository;
import com.upc.finanzas.repository.PeriodRepository;
import com.upc.finanzas.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeriodServiceImpl implements PeriodService {
    @Autowired
    private PeriodRepository periodRepository;

    @Transactional
    @Override
    public Period save(Period entity) throws Exception {
        return periodRepository.save(entity);
    }
    @Transactional
    @Override
    public List<Period> findAll() throws Exception {
        return periodRepository.findAll();
    }
    @Transactional
    @Override
    public Period findById(Long aLong) throws Exception {
        return ((periodRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("period", "Id", aLong))));
    }
    @Transactional
    @Override
    public Period update(Period entity, Long id) throws Exception {
        entity.setId(id);
        return periodRepository.save(entity);
    }
    @Transactional
    @Override
    public void deleteById(Long aLong) throws Exception {
        periodRepository.deleteById(aLong);
    }
}
