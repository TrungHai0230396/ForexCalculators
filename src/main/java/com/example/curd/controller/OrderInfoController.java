package com.example.curd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import com.example.curd.exception.ResourceNotFoundException;
import com.example.curd.models.OrderInfo;
import com.example.curd.models.ResponseObject;
import com.example.curd.services.OrderInfoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/OrderInfo")
public class OrderInfoController {
    @Autowired 
    private OrderInfoService orderInfoService;
    
    @GetMapping("")
    //this request is: http://localhost:8005/api/OrderInfo
    List<OrderInfo> getAllOrderInfos() {
       return orderInfoService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        try {
            OrderInfo foundOrderInfo = orderInfoService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query OrderInfo successfully", foundOrderInfo));
        
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", e.getMessage(), ""));
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertOrderInfo(@RequestBody @Valid OrderInfo newOrderInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", bindingResult.getAllErrors().get(0).getDefaultMessage(), "")
            );
        }

        newOrderInfo = orderInfoService.calculateResult(newOrderInfo);
        try {
            orderInfoService.save(newOrderInfo);
            return ResponseEntity.status(HttpStatus.CREATED).body(
               new ResponseObject("created", "Insert OrderInfo successfully", newOrderInfo)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", "Insert OrderInfo successfully", newOrderInfo)
            );
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateOrderInfo(@RequestBody OrderInfo newOrderInfo, @PathVariable Long id) {
        try {
            OrderInfo updatedOrderInfo = orderInfoService.findById(id);
            updatedOrderInfo.setPipValuePerLot(newOrderInfo.getPipValuePerLot());
            updatedOrderInfo.setStopLoss(newOrderInfo.getStopLoss());
            updatedOrderInfo.setEntry(newOrderInfo.getEntry());
            updatedOrderInfo.setTakeProfit(newOrderInfo.getTakeProfit());
            updatedOrderInfo.setAmoutToRisk(newOrderInfo.getAmoutToRisk());
            updatedOrderInfo = orderInfoService.calculateResult(updatedOrderInfo);
            orderInfoService.update(updatedOrderInfo);

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update OrderInfo successfully", updatedOrderInfo)
        );
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", e.getMessage(), ""));
        }
    }

    @PutMapping("/updateResult/{id}")
    ResponseEntity<ResponseObject> updateResult(@PathVariable Long id,  @RequestParam("result") Float result) {
        try {
            OrderInfo foundOrderInfo = orderInfoService.findById(id);
            foundOrderInfo.setResult(result);
            orderInfoService.update(foundOrderInfo);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update OrderInfo result successfully", foundOrderInfo)
            );
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", e.getMessage(), ""));
        }
    }

    //Delete a OrderInfo => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteOrderInfo(@PathVariable Long id) {
        boolean exists = orderInfoService.existsById(id);
        if(exists) {
            orderInfoService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete OrderInfo successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Cannot find OrderInfo to delete", "")
        );
    }
}
