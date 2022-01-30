package com.patika.customerservice.controller;

import com.common.dto.CustomerDetailDTO;
import com.patika.customerservice.publisher.MessagePublisher;
import com.patika.customerservice.service.CustomerDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerDetailController {

    @Autowired
    private MessagePublisher messagePublisher;

    @Autowired
    private CustomerDetailServiceImpl customerDetailService;

    @PostMapping("/create")
    public ResponseEntity<String> createApplication(@RequestBody CustomerDetailDTO customerDetailDTO) {

        try{
            CustomerDetailDTO savedCustomer = customerDetailService.save(customerDetailDTO);
            messagePublisher.sendCustomerDetail(savedCustomer);
            return ResponseEntity.ok("success");
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestParam Long nationalId, @RequestBody CustomerDetailDTO customerDetailDTO ) {

        try {
            CustomerDetailDTO savedCustomer = customerDetailService.update(nationalId,customerDetailDTO);
            messagePublisher.updateCustomer(savedCustomer);
            return new ResponseEntity<>("success", null, 200);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteApplication(@RequestParam Long nationalId, @RequestParam String birthDay) {

        try {
            messagePublisher.deleteApplicationDetail(nationalId);
            customerDetailService.delete(nationalId, LocalDate.parse(birthDay));
            return new ResponseEntity<String>("success", null, 200);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/detail/{nationalId}/{date}")
    public ResponseEntity<String> get(@PathVariable Long nationalId, @PathVariable String date ){

        try {
            customerDetailService.findByNationalIdAndBirthday(nationalId,LocalDate.parse(date));
            return new ResponseEntity<>("success", null, 200);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
