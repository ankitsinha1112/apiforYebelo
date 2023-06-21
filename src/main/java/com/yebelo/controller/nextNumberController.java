package com.yebelo.controller;

import com.yebelo.entity.nextNumberEntity;
import com.yebelo.entity.nextNumberResponse;
import com.yebelo.service.nextNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class nextNumberController {

    @Autowired
    private nextNumberService nextnumberService;

    //This is for testing part
    @GetMapping("/")
    public String home() {
        return "This is home page";
    }

    //This is for fetching Next Number in the table
    @PostMapping("/fetchNextNumber")
    public ResponseEntity<nextNumberResponse> fetchNextNumber(@RequestBody nextNumberEntity nextNumberEntity) {
        int oldValue = nextnumberService.fetchValueByCategoryCode(nextNumberEntity.getCategoryCode());
        int newValue = nextnumberService.calculateNextNumber(nextNumberEntity.getCategoryCode(),oldValue);
        nextNumberResponse response = new nextNumberResponse(oldValue, newValue);
        return ResponseEntity.ok(response);
    }
}