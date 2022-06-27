package com.upc.finanzas.controller;

import com.upc.finanzas.entity.Bono;
import com.upc.finanzas.service.BonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bono")
public class bonoController {
    @Autowired
    private BonoService bonoService;
    @CrossOrigin
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bono> fetchById(@PathVariable("id") Long id) {
        try {
            Bono bono = bonoService.findById(id);
            if(bono != null){
                return ResponseEntity.ok(bono);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Bono> save(@Valid @RequestBody Bono bono , BindingResult result) {

        try {
            Bono bonoCreate = bonoService.save(bono);
            return ResponseEntity.status(HttpStatus.CREATED).body(bonoCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<Bono> update(@PathVariable("id") Long id, @RequestBody Bono bono) {
        try {
            Bono bonoDB = bonoService.findById(id);
            if (bonoDB != null) {
                Bono bonoCreate = bonoService.update(bono, id);
                return ResponseEntity.status(HttpStatus.CREATED).body(bonoCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Bono> deleteById(@PathVariable("id") Long id){


        try {
            Bono optionalBono = bonoService.findById(id);
            if(optionalBono != null){
                bonoService.deleteById(id);
                return new ResponseEntity<Bono>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @GetMapping(path = "/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bono>> getByUserId(@PathVariable("userId") Long userId) throws Exception {
        List<Bono> bonos = bonoService.getByUserId(userId);
        if ( null == bonos ) {
            return  ResponseEntity.notFound().build();
        }

       return ResponseEntity.ok(bonos);
    }

}
