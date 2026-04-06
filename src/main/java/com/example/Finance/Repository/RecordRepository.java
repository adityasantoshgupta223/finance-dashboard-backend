package com.example.Finance.Repository;

import com.example.Finance.Models.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<FinancialRecord, Long> {

  @Query("SELECT f FROM FinancialRecord f WHERE f.createdBy.id = :userId")
  List<FinancialRecord> getRecordByUserId(@Param("userId") long userId);

}
