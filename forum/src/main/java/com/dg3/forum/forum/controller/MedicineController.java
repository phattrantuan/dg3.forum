package com.dg3.forum.forum.controller;

import com.dg3.forum.forum.entity.Medicine;
import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @PostMapping("/create/medicine")
    public ResponseEntity<Message> createMedicine(@RequestBody Medicine medicine){
        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("OK", "Create medicine succesfully", medicineService.createMedicine(medicine))
        );
    }

    @PutMapping("/update/medicine")
    public ResponseEntity<Message> updateMedicine(@RequestBody Medicine medicine){
        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("OK", "Create medicine succesfully", "")
        );
    }
}
