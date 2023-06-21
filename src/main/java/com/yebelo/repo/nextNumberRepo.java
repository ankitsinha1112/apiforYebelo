package com.yebelo.repo;

import com.yebelo.entity.nextNumberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface nextNumberRepo extends JpaRepository<nextNumberEntity,Long> {
    nextNumberEntity findByCategoryCode(String categoryCode);

    @Modifying
    @Transactional
    @Query("UPDATE nextNumberEntity n SET n.value = :value WHERE n.categoryCode = :categoryCode")
    void updateValue(@Param("categoryCode") String categoryCode, @Param("value") int value);
}