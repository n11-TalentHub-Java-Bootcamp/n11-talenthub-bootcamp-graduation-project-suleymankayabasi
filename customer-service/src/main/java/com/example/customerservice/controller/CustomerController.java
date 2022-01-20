package com.example.customerservice.controller;

import com.example.customerservice.dto.CustomerDTO;
import com.example.customerservice.dto.DepositDTO;
import com.example.customerservice.service.CustomerServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    //todo: @valid anatasyonunu ekle validaston yapılabilir ama enson()


    private final CustomerServiceImpl customerService;

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.update(id,customerDTO));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.save(customerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        customerService.deleteByCustomerID(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAllCustomer(){
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findCustomer(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findByCustomerID(id));
    }

    @GetMapping("/national-id/{nationalID}")
    public ResponseEntity<CustomerDTO> checkCustomer(@PathVariable Long nationalID,
                                                     @RequestParam(name= "birthday", defaultValue="")String dateString){
        String pattern ="yyyy-MM-dd";
        LocalDate birthday = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
        return ResponseEntity.ok(customerService.checkValidCustomer(nationalID,birthday));
    }

    @GetMapping("/deposits/{id}")
    public ResponseEntity<List<DepositDTO>> findAllDepositByCustomerId(@PathVariable Long id){
        return  ResponseEntity.ok(customerService.findAllDepositByCustomerId(id));
    }

}