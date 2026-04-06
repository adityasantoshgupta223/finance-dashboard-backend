package com.example.Finance.Controller;

import com.example.Finance.Models.FinancialRecord;
import com.example.Finance.Models.Users;
import com.example.Finance.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private RecordService service;

    @GetMapping("/record")
    public List<FinancialRecord> getAllRecord(@RequestParam long requestedBy){
        return service.getAllRecord(requestedBy);
    }

    @GetMapping("/record/user/{userId}")
    public List<FinancialRecord> getAllUserRecord(@PathVariable long userId, @RequestParam long requestedBy){
        return service.getAllUserRecord(userId, requestedBy);
    }

    @GetMapping("/record/{recordId}")
    public FinancialRecord getRecord( @PathVariable long recordId, @RequestParam long requestedBy){
        return service.getRecord(requestedBy, recordId);
    }

    @PostMapping("/record")
    public ResponseEntity<String> createRecord(@RequestParam long createdBy, @RequestBody FinancialRecord record){
        return service.createRecord(createdBy, record);
    }

    @PutMapping("/record/{id}")
    public ResponseEntity<String> updateRecord(@PathVariable long recordId, @RequestParam long requestedBy, @RequestBody FinancialRecord record){
        return service.updateRecord(recordId, requestedBy, record);
    }

    @DeleteMapping("/record/{recordId}")
    public ResponseEntity<String> deleteRecord(@PathVariable long recordId, @RequestParam long requestedBy){
        return service.deleteRecord(recordId, requestedBy);
    }

}
