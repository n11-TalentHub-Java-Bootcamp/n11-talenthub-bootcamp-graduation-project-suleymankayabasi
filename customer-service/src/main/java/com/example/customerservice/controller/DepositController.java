package com.example.customerservice.controller;

import com.example.customerservice.dto.DepositDTO;
import com.example.customerservice.service.DepositServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/deposits")
public class DepositController {

    private final DepositServiceImpl depositService;

    @GetMapping
    public ResponseEntity<List<DepositDTO>> findAllDeposit(){
        return ResponseEntity.ok(depositService.findAllDeposit());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DepositDTO>> findDepositByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(depositService.findAllDepositByCustomerId(id));
    }

    @PostMapping
    public ResponseEntity<DepositDTO> saveDeposit(@RequestBody DepositDTO depositDTO){
        return ResponseEntity.ok(depositService.save(depositDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepositDTO> updateDeposit(@PathVariable Long id, @RequestBody DepositDTO depositDTO){
        return ResponseEntity.ok(depositService.update(id,depositDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeposit(@PathVariable Long id){
        depositService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
