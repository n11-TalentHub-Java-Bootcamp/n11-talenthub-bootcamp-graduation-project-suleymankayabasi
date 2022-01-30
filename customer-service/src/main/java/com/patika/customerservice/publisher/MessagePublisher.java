package com.patika.customerservice.publisher;

import com.common.dto.CustomerDetailDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Qualifier("customerQueue")
    @Autowired
    private Queue customerQueue;

    @Qualifier("deleteQueue")
    @Autowired
    private Queue deleteQueue;

    public void sendCustomerDetail(CustomerDetailDTO customerDetailDTO) {
        rabbitTemplate.convertAndSend(customerQueue.getActualName(), customerDetailDTO);
    }

    public void deleteApplicationDetail(Long nationalId) {
        rabbitTemplate.convertAndSend(deleteQueue.getActualName(), nationalId );
    }
    public void updateCustomer(CustomerDetailDTO customerDetailDTO){
        rabbitTemplate.convertAndSend(customerQueue.getActualName(), customerDetailDTO);
    }
}
