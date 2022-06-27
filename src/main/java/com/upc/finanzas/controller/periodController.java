package com.upc.finanzas.controller;

import com.upc.finanzas.entity.Period;
import com.upc.finanzas.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/period")
public class periodController {
    @Autowired
    private PeriodService periodService;
    @CrossOrigin
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Period> fetchById(@PathVariable("id") Long id) {
        try {
            Period period = periodService.findById(id);
            if(period != null){
                return ResponseEntity.ok(period);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Period> save(@Valid @RequestBody Period period, BindingResult result) {

        try {
            Period periodCreate = periodService.save(period);
            return ResponseEntity.status(HttpStatus.CREATED).body(periodCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<Period> update(@PathVariable("id") Long id, @RequestBody Period period) {
        try {
            Period periodDB = periodService.findById(id);
            if (period != null) {
                Period periodCreate = periodService.update(period, id);
                return ResponseEntity.status(HttpStatus.CREATED).body(periodCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Period> deleteById(@PathVariable("id") Long id){


        try {
            Period optionalInflation = periodService.findById(id);
            if(optionalInflation != null){
                periodService.deleteById(id);
                return new ResponseEntity<Period>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
