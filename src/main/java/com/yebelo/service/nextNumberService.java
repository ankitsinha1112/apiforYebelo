package com.yebelo.service;
import com.yebelo.entity.nextNumberEntity;
import com.yebelo.repo.nextNumberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class nextNumberService {

    private nextNumberRepo nextNumberRepo;

    @Autowired
    public nextNumberService(nextNumberRepo nextNumberRepo) {
        this.nextNumberRepo = nextNumberRepo;
    }

    public int fetchValueByCategoryCode(String categoryCode) {
        nextNumberEntity numberEntity = nextNumberRepo.findByCategoryCode(categoryCode);
        return numberEntity != null ? numberEntity.getValue() : 0;
    }

    public synchronized int calculateNextNumber(int currentValue) {
        try {
            Thread.sleep(5000);  // 5-second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int nextNumber = currentValue + 9;   //It follows a specific pattern whose immediate successor is 9 more than predecessor
        nextNumberRepo.updateValue(nextNumber);
        return nextNumber;
    }
}