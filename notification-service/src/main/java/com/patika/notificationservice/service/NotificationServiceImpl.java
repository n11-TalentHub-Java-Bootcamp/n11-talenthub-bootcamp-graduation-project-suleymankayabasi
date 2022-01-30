package com.patika.notificationservice.service;

import com.common.dto.ApplicationDetailDTO;
import com.common.dto.NotificationDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Override
    public NotificationDetailDTO sendNotification(ApplicationDetailDTO applicationDetailDTO) {

        NotificationDetailDTO notificationDetailDTO = new NotificationDetailDTO();

        try {
            if(applicationDetailDTO.isApprovalStatus()){
                log.info("Application approved");
                notificationDetailDTO.setStatus(true);
                notificationDetailDTO.setMessage("Dear Customer," +
                        "You are Qualified for $" + applicationDetailDTO.getCreditLimit().toString() +" loan. " +
                        "The loan amount will be sent to your bank account within 12 working hours after all the procedures have been completed." +
                        "Thank You for choosing Loans Turkey");
                notificationDetailDTO.setCreatedAt(LocalDate.now());
                return notificationDetailDTO;
            }
            else{
                log.info("Application rejected");
                notificationDetailDTO.setStatus(false);
                notificationDetailDTO.setMessage("Unfortunately, " +
                        "You dont have enough criteria for approval." +
                        "Thank You for choosing Loans Turkey");
                notificationDetailDTO.setCreatedAt(LocalDate.now());
                return notificationDetailDTO;
            }
        }catch (Exception e){
            throw new RuntimeException("Application seems null");
        }
    }
}
