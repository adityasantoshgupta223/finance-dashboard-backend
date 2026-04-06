package com.example.Finance.DTO;

import com.example.Finance.Models.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


@Getter
@Setter
@RestControllerAdvice
public class DashboardDTO {

    private  String userName;
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
    private HashMap<String, Double> categoryTotal;


}
