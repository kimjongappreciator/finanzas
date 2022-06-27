package com.upc.finanzas.controller;

import com.upc.finanzas.entity.Inflation;
import com.upc.finanzas.service.InflationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inflation")
public class inflationController {
    @Autowired
    private InflationService inflationService;
    @CrossOrigin
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inflation> fetchById(@PathVariable("id") Long id) {
        try {
            Inflation inflation = inflationService.findById(id);
            if(inflation != null){
                return ResponseEntity.ok(inflation);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Inflation> save(@Valid @RequestBody Inflation inflation, BindingResult result) {

        try {
            Inflation inflationCreate = inflationService.save(inflation);
            return ResponseEntity.status(HttpStatus.CREATED).body(inflationCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<Inflation> update(@PathVariable("id") Long id, @RequestBody Inflation inflation) {
        try {
            Inflation inflationDB = inflationService.findById(id);
            if (inflationDB != null) {
                Inflation inflationCreate = inflationService.update(inflation, id);
                return ResponseEntity.status(HttpStatus.CREATED).body(inflationCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
       }
    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Inflation> deleteById(@PathVariable("id") Long id){


        try {
            Inflation optionalInflation = inflationService.findById(id);
            if(optionalInflation != null){
                inflationService.deleteById(id);
                return new ResponseEntity<Inflation>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @GetMapping(path = "/bonoId/{bonoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Inflation>> getBybonoId(@PathVariable("bonoId") Long bonoId) throws Exception {
        List<Inflation> inflations = inflationService.getByBonoId(bonoId);
        if ( null == inflations ) {
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inflations);
    }
}
