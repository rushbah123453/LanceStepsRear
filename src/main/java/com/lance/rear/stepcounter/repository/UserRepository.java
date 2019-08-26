package com.lance.rear.stepcounter.repository;

import com.lance.rear.stepcounter.model.StepHistory;
import com.lance.rear.stepcounter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into User (uniqueCode, name) values (:uniqueCode, :name)", nativeQuery = true)
    void signUp(@Param("uniqueCode") Integer uniqueCode, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "insert into StepHistory (stepId, uniqueCode, steps, timeInMillis) values (NULL, :uniqueCode, :steps, :timeInMillis)", nativeQuery = true)
    int noteSteps(@Param("uniqueCode") Integer uniqueCode, @Param("steps") Integer steps, @Param("timeInMillis") Long timeInMillis);

    @Query(value = "select uniqueCode, steps, timeInMillis from StepHistory where uniqueCode = :uniqueCode", nativeQuery = true)
    Collection<Object> getStepHistory(@Param("uniqueCode") Integer uniqueCode);
}
