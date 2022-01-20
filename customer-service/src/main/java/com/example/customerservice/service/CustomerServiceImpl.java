package com.example.customerservice.service;

import com.example.customerservice.dto.CustomerDTO;
import com.example.customerservice.dto.DepositDTO;
import com.example.customerservice.enums.ErrorMessage;
import com.example.customerservice.exception.CustomerIsAlreadyExistException;
import com.example.customerservice.exception.CustomerNotFoundException;
import com.example.customerservice.exception.DepositNotFoundException;
import com.example.customerservice.mapper.CustomerMapper;
import com.example.customerservice.mapper.DepositMapper;
import com.example.customerservice.model.Customer;
import com.example.customerservice.model.Deposit;
import com.example.customerservice.repository.CustomerRepository;
import com.example.customerservice.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService{

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final DepositRepository depositRepository;

    @Override
    public CustomerDTO update(Long id,CustomerDTO customerDTO) {
        logger.info("Customer Service updating process is started");
        if(customerRepository.findById(id).isPresent()){
            Customer customer = customerRepository.findById(id).get();
            customer.setCreditScore(customerDTO.getCreditScore());
            customer.setIncome(customerDTO.getIncome());
            customer.setPhoneNumber(customerDTO.getPhoneNumber());
            customer.setUpdateDate(LocalDate.now());
            customer = customerRepository.save(customer);
            CustomerDTO customerDTOResponse = CustomerMapper.INSTANCE.convertCustomerToCustomerDTO(customer);
            logger.info("Customer Service updating process is done successfully : " + customerDTOResponse.toString());
            return customerDTOResponse;
        }
        else {
            logger.error("Customer service updating process error : " + ErrorMessage.CUSTOMER_IS_NOT_FOUND);
            throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_IS_NOT_FOUND.toString());
        }
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        logger.info("Customer Service saving process is started");
        boolean isExist = customerRepository.existsCustomerById(customerDTO.getId());
        if(isExist){
            logger.error("Customer service saving process error : "+ ErrorMessage.CUSTOMER_IS_ALREADY_EXIST);
            throw new CustomerIsAlreadyExistException(ErrorMessage.CUSTOMER_IS_ALREADY_EXIST.toString());
        }
        Customer customer = CustomerMapper.INSTANCE.convertCustomerDTOtoCustomer(customerDTO);
        customer = customerRepository.save(customer);
        CustomerDTO customerDTOResponse = CustomerMapper.INSTANCE.convertCustomerToCustomerDTO(customer);
        logger.info("Customer Service saving process is done successfully : " + customerDTOResponse.toString());
        return customerDTOResponse;
    }

    @Override
    public void deleteByCustomerID(Long id) {
        logger.info("Customer Service deleting process is started");
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent()){
            logger.error("Customer service deleting process error : " + ErrorMessage.CUSTOMER_IS_NOT_FOUND);
            throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_IS_NOT_FOUND.toString());
        }
        logger.info("Customer Service deleting process is done successfully");
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> findAllCustomer() {
        logger.info("Customer Service finding process is started");
        List<Customer> customerList = customerRepository.findAll();
        if(customerList.isEmpty()){
            logger.error("Customer service finding process error : " + ErrorMessage.CUSTOMER_IS_NOT_FOUND);
            throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_IS_NOT_FOUND.toString());
        }
        List<CustomerDTO> customerDTOList = CustomerMapper.INSTANCE.convertCustomerListToCustomerDTOList(customerList);
        logger.info("Customer Service finding process is done successfully :" +customerDTOList.toString());
        return customerDTOList;
    }

    @Override
    public CustomerDTO findByCustomerID(Long id) {
        logger.info("Customer Service finding process is started");
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            CustomerDTO customerDTOResponse = CustomerMapper.INSTANCE.convertCustomerToCustomerDTO(customer.get());
            logger.info("Customer Service finding process is done successfully :" +customerDTOResponse.toString());
            return customerDTOResponse;
        }
        logger.error("Customer service finding process error : " + ErrorMessage.CUSTOMER_IS_NOT_FOUND);
        throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_IS_NOT_FOUND.toString());
    }

    @Override
    public CustomerDTO checkValidCustomer(Long nationalId, LocalDate birthday) {
        logger.info("Customer Service checking process is started");
        Customer customer = customerRepository.findCustomerByNationalId(nationalId);
        if(customer == null){
            logger.error("Customer service finding process error : " + ErrorMessage.CUSTOMER_IS_NOT_FOUND);
            throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_IS_NOT_FOUND.toString());
        }
        if(customer.getBirthDay().equals(birthday)){
            CustomerDTO customerDTO = CustomerMapper.INSTANCE.convertCustomerToCustomerDTO(customer);
            logger.info("Customer Service checking process is done successfully :" +customerDTO.toString());
            return customerDTO ;
        }
        logger.error("Customer service checking process error : " + ErrorMessage.CUSTOMER_MATCH_NOT_FOUND);
        throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_MATCH_NOT_FOUND.toString());
    }

    @Override
    public List<DepositDTO> findAllDepositByCustomerId(Long id) {
        logger.info("Customer Service finding process is started");
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            if(depositRepository.findAllByCustomer_Id(customer.get().getId()).isEmpty()){
                logger.error("Customer service finding process error : " + ErrorMessage.DEPOSIT_IS_NOT_FOUND);
                throw new DepositNotFoundException(ErrorMessage.DEPOSIT_IS_NOT_FOUND.toString());
            }
            else{
                List<Deposit> depositList =  depositRepository.findAllByCustomer_Id(id);
                List<DepositDTO> depositDTOList = DepositMapper.INSTANCE.convertDepositListToDepositDTOList(depositList);
                logger.info("Customer Service finding process is done successfully :" + depositDTOList.toString());
                return depositDTOList;
            }
        }
        logger.error("Customer service finding process error : " + ErrorMessage.CUSTOMER_IS_NOT_FOUND);
        throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_IS_NOT_FOUND.toString());
    }
}
