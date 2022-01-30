package com.patika.creditservice.publisher;

import com.common.dto.ApplicationDetailDTO;
import com.patika.creditservice.config.MessagingConfig;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void sendApplicationDetail(ApplicationDetailDTO applicationDetailDTO) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, applicationDetailDTO);
    }

}
