package com.patika.notificationservice.consumer;

import com.common.dto.ApplicationDetailDTO;
import com.common.dto.NotificationDetailDTO;
import com.patika.notificationservice.service.NotificationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {

    @Autowired
    private NotificationServiceImpl notificationService;

    @RabbitListener(queues = "application")
    public void receive(@Payload ApplicationDetailDTO applicationDetailDTO) {
        try{
            log.info("Received message: {}", applicationDetailDTO);
            NotificationDetailDTO notificationDetailDTO = notificationService.sendNotification(applicationDetailDTO);
            log.info("Notification sent: {}", notificationDetailDTO);
        }catch (Exception e){
            throw new RuntimeException("Notification Queue is not working");
        }
    }
}
