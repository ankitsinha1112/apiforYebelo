package com.yebelo.repo;

import com.yebelo.entity.nextNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface nextNumberRepo extends JpaRepository<nextNumberEntity, Long> {
    nextNumberEntity findByCategoryCode(String categoryCode);
    void updateValue(int newValue);
}