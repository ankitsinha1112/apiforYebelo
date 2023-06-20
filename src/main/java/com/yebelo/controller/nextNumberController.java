package com.yebelo.controller;

import com.yebelo.entity.nextNumberEntity;
import com.yebelo.entity.nextNumberResponse;
import com.yebelo.service.nextNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class nextNumberController {

    @Autowired
    private nextNumberService nextnumberService;

    @PostMapping("/fetchNextNumber")
    public ResponseEntity<nextNumberResponse> fetchNextNumber(@RequestBody nextNumberEntity nextNumberEntity) {
        int oldValue = nextnumberService.fetchValueByCategoryCode(nextNumberEntity.getCategoryCode());
        int newValue = nextnumberService.calculateNextNumber(oldValue);
        nextNumberResponse response = new nextNumberResponse(oldValue, newValue);
        return ResponseEntity.ok(response);
    }
}