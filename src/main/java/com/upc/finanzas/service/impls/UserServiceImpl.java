package com.upc.finanzas.service.impls;

import com.upc.finanzas.entity.User;
import com.upc.finanzas.exception.ResourceNotFoundException;
import com.upc.finanzas.repository.UserRepository;
import com.upc.finanzas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User save(User entity) throws Exception {
        return userRepository.save(entity);
    }
    @Transactional
    @Override
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }
    @Transactional
    @Override
    public User findById(Long aLong) throws Exception {
        return ((userRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("user", "Id", aLong))));
    }
    @Transactional
    @Override
    public User update(User entity, Long id) throws Exception {
        //return bonoRepository.save(entity);
        entity.setId(id);
        return this.userRepository.save(entity );
    }
    @Transactional
    @Override
    public void deleteById(Long aLong) throws Exception {
        userRepository.deleteById(aLong);
    }

    @Transactional
    @Override
    public User getByEmail(String email){
        return userRepository.getByEmail(email);
    }
}
