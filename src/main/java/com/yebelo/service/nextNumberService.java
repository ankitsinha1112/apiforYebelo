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
        if(numberEntity != null)
        {
            nextNumberRepo.save(numberEntity);
            return numberEntity.getValue();
        }
        else{
            return 0;
        }
    }

    public synchronized int calculateNextNumber(String categoryCode,int currentValue) {
        try {
            Thread.sleep(5000);  // 5-second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int nextNumber;
        int remainder = (currentValue % 9);
        if(remainder == 0) {
            // if Value is multiple of 9 then nextNumber i.e value+1 will have digit sum == 1
            // e.g. 18 % 9 = 0, => 19 = 1 + 9 = 10, => 1 + 0 = 1, so it satisfies the condition
            nextNumber = currentValue + 1;
        }
        else {
            // if not multiple of 9, find the next nearest multiple of 9, then add one to it
            nextNumber = currentValue + (9 - (remainder - 1));
            // e.g. 25 % 9 = 7,  so next number that satisfies the condition : 25 + (9-(7-1)) = 25 + 3 = 28, 2 + 8 = 10, 1 + 0 = 1
        }
        nextNumberRepo.updateValue(categoryCode, nextNumber);
        return nextNumber;
    }
}