package com.example.curd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.curd.models.OrderInfo;
import com.example.curd.models.PipValuePerLot;
import com.example.curd.models.ResponseObject;
import com.example.curd.repositories.OrderInfoRepository;
import com.example.curd.repositories.PipValuePerLotRepository;
import com.example.curd.services.OrderInfoService;

@CrossOrigin(origins = "http://localhost:4201", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/OrderInfo")
public class OrderInfoController {
    @Autowired 
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderInfoRepository orderInforepository;
    
    @Autowired
    private PipValuePerLotRepository  pipValuePerLotRepository;

    @GetMapping("")
    //this request is: http://localhost:8005/api/OrderInfo
    List<OrderInfo> getAllOrderInfos() {
       return orderInforepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<OrderInfo> foundOrderInfo = orderInforepository.findById(id);
        return foundOrderInfo.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query OrderInfo successfully", foundOrderInfo)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find OrderInfo with id = "+id, "")
                );
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertOrderInfo(@RequestBody OrderInfo newOrderInfo) {
        Optional<PipValuePerLot> pipValuePerLot = pipValuePerLotRepository.findById(newOrderInfo.getPipValuePerLot().getId());
        newOrderInfo.setPipValuePerLot(pipValuePerLot.get());
        newOrderInfo = orderInfoService.calculateResult(newOrderInfo);
        return ResponseEntity.status(HttpStatus.OK).body(
           new ResponseObject("ok", "Insert OrderInfo successfully", orderInforepository.save(newOrderInfo))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateOrderInfo(@RequestBody OrderInfo newOrderInfo, @PathVariable Long id) {
        OrderInfo updatedOrderInfo = orderInforepository.findById(id)
                .map(orderInfo -> {
                    orderInfo.setPipValuePerLot(newOrderInfo.getPipValuePerLot());
                    orderInfo.setStopLoss(newOrderInfo.getStopLoss());
                    orderInfo.setEntry(newOrderInfo.getEntry());
                    orderInfo.setTakeProfit(newOrderInfo.getTakeProfit());
                    orderInfo.setAmoutToRisk(newOrderInfo.getAmoutToRisk());
                    orderInfo = orderInfoService.calculateResult(orderInfo);
                    return orderInforepository.save(orderInfo);
                }).orElseGet(() -> {
                    newOrderInfo.setId(id);
                    return orderInforepository.save(newOrderInfo);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update OrderInfo successfully", updatedOrderInfo)
        );
    }

    @PutMapping("/updateResult/{id}")
    ResponseEntity<ResponseObject> updateResult(@PathVariable Long id,  @RequestParam("result") Float result) {
        Optional<OrderInfo> foundOrderInfo = orderInforepository.findById(id);
        if(foundOrderInfo.isPresent()) {
            foundOrderInfo.get().setResult(result);
            orderInforepository.save(foundOrderInfo.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update OrderInfo successfully", foundOrderInfo)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Cannot find OrderInfo to delete", "")
        );
    }

    //Delete a OrderInfo => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteOrderInfo(@PathVariable Long id) {
        boolean exists = orderInforepository.existsById(id);
        if(exists) {
            orderInforepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete OrderInfo successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Cannot find OrderInfo to delete", "")
        );
    }
}
