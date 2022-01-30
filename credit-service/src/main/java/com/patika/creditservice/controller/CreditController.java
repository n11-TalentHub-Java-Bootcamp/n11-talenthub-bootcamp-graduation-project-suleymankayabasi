package com.patika.creditservice.controller;

import com.common.dto.ApplicationDetailDTO;
import com.patika.creditservice.service.ApplicationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/credit")
@RestController
@Slf4j
public class CreditController {

    private final static String URL = "http://localhost:8080/customer/detail/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @GetMapping("/get")
    public ResponseEntity<List<ApplicationDetailDTO>> getApplications(@RequestParam("nationalId") Long nationalId, @RequestParam String birthDay) {

        try {
            ResponseEntity<String>  response =  restTemplate.getForEntity(URL+nationalId+"/"+birthDay, String.class);
            List<ApplicationDetailDTO> applicationDetailDTOList = applicationService.findApplicationDetailsByNationalId(nationalId);
            return ResponseEntity.ok(applicationDetailDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}