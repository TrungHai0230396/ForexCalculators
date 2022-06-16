package com.example.curd.controller;
import  com.example.curd.models.PipValuePerLot;
import com.example.curd.repositories.PipValuePerLotRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4201", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/PipValuePerLot")
public class PipValuePerLotController {
    @Autowired
    private PipValuePerLotRepository repository;

    @GetMapping("")
    //this request is: http://localhost:8005/api/PipValuePerLot
    List<PipValuePerLot> getAllPipValuePerLots() {
       return repository.findAll();
    }

}
