package com.yebelo.repo;

import com.yebelo.entity.nextNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface nextNumberRepo extends JpaRepository<nextNumberEntity, Long> {
    nextNumberEntity findByCategoryCode(String categoryCode);

    @Modifying
    @Query("UPDATE nextNumberEntity n SET n.value = :value")
    void updateValue(@Param("value") int value);
}