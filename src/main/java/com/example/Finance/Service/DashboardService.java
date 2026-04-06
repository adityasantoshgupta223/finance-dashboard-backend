package com.example.Finance.Service;

import com.example.Finance.DTO.DashboardDTO;
import com.example.Finance.Models.FinancialRecord;
import com.example.Finance.Models.enums.RecordType;
import com.example.Finance.Repository.RecordRepository;
import com.example.Finance.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private RecordRepository repo;

    @Autowired
    private DashboardDTO dashboardDTO;

  public DashboardDTO getUserDashboard(long requestedFor){

      List<FinancialRecord> records = repo.getRecordByUserId(requestedFor);

      Double income = getIncome(records);
      Double expense = getExpense(records);
      Double balance = income - expense;
      HashMap<String, Double> categoryTotal = getCategoryTotal(records);

      dashboardDTO.setUserName(records.getFirst().getCreatedBy().getName());
      dashboardDTO.setTotalIncome(income);
      dashboardDTO.setTotalExpense(expense);
      dashboardDTO.setBalance(balance);
      dashboardDTO.setCategoryTotal(categoryTotal);

      return dashboardDTO;
  }

  public Double getIncome(List<FinancialRecord> records){
      Double income = 0.0;
      for(FinancialRecord record : records){
          if(record.getRecordType() == RecordType.INCOME) income += record.getAmount();
      }
      return income;
  }

  public Double getExpense(List<FinancialRecord> records){
      Double expense = 0.0;
      for(FinancialRecord record : records){
          if(record.getRecordType() == RecordType.EXPENSE) expense += record.getAmount();
      }
      return expense;
  }


  // "-ve" values means user's expense amt > income amt
  public HashMap<String, Double> getCategoryTotal(List<FinancialRecord> records){
      HashMap<String, Double> categoryTotal = new HashMap<>();

      for(FinancialRecord record : records){
         String category = record.getCategory();
           if(record.getRecordType() == RecordType.INCOME) {
               categoryTotal.put(category, categoryTotal.getOrDefault(category, 0.0) + record.getAmount());
           } else{
               categoryTotal.put(category, categoryTotal.getOrDefault(category, 0.0) - record.getAmount());
           }
      }
      return categoryTotal;
  }
}
