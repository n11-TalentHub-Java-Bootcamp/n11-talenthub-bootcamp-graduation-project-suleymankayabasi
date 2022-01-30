package com.patika.creditservice.consumer;

import com.common.dto.ApplicationDetailDTO;
import com.common.dto.CustomerDetailDTO;
import com.patika.creditservice.exception.ApplicationDetailNotFoundException;
import com.patika.creditservice.model.ApplicationDetail;
import com.patika.creditservice.publisher.MessagePublisher;
import com.patika.creditservice.repository.ApplicationDetailRepository;
import com.patika.creditservice.service.ApplicationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RabbitMQConsumer {

    @Autowired
    private MessagePublisher messagePublisher;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @Autowired
    private ApplicationDetailRepository applicationDetailRepository;

    @Autowired
    public RabbitMQConsumer(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @RabbitListener(queues = "customer")
    public void receiveCustomerDetail(@Payload CustomerDetailDTO customerDetailDTO) {

        try {
            log.info("Consumed : " + customerDetailDTO.toString());
            ApplicationDetailDTO applicationDetailDTO = applicationService.calculateApplicationDetail(customerDetailDTO);
            messagePublisher.sendApplicationDetail(applicationDetailDTO);
            log.info("Published : " + applicationDetailDTO.toString());
        } catch (Exception exception){
            throw new RuntimeException("Customer queue is not working");
        }
    }

    @RabbitListener(queues = "delete_application")
    public void deleteApplicationDetail(@Payload Long nationalId) {

        try{
            log.info("Consumed : " + nationalId);
            List<ApplicationDetail> applicationDetailList = applicationDetailRepository.findAllByNationalId(nationalId);
            if (applicationDetailList.isEmpty()) {
                log.error("Application is not found with national id : " + nationalId);
                throw new ApplicationDetailNotFoundException("Application detail is not found");
            } else {
                log.info("Deleted User : " + nationalId);
                log.info("Deleted Application List: " + applicationDetailList.toString());
                applicationService.deleteApplicationDetail(nationalId);
            }
        } catch (Exception e){
            throw new RuntimeException("Delete Queue is not working");
        }
    }

    @RabbitListener(queues = "customer")
    public void updateCustomerDetail(@Payload CustomerDetailDTO customerDetailDTO){

        try {
            log.info("Consumed : " + customerDetailDTO.toString());
            ApplicationDetailDTO applicationDetailDTO = applicationService.calculateApplicationDetail(customerDetailDTO);
            log.info("Published (Updated) : " + applicationDetailDTO.toString());
        } catch (Exception e){
            throw new RuntimeException("Update Queue is not working");
        }
    }
}
