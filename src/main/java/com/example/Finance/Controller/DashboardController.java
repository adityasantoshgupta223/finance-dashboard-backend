package com.example.Finance.Controller;

import com.example.Finance.DTO.DashboardDTO;
import com.example.Finance.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping("/dashboard/users/{userId}")
   public DashboardDTO getUserDashboard(@PathVariable long userId){
         return service.getUserDashboard(userId);
    }

}
