package com.example.curd.controller;
import  com.example.curd.models.PipValuePerLot;
import com.example.curd.services.PipValuePerLotService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/PipValuePerLot")
public class PipValuePerLotController {
    @Autowired
    private PipValuePerLotService pipValuePerLotService;

    @GetMapping("")
    //this request is: http://localhost:8005/api/PipValuePerLot
    List<PipValuePerLot> getAllPipValuePerLots() {
       return pipValuePerLotService.findAll();
    }

}
