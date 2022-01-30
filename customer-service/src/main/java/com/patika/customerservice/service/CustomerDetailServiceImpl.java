package com.patika.customerservice.service;

import com.common.dto.CustomerDetailDTO;
import com.patika.customerservice.exception.CustomerIsExistException;
import com.patika.customerservice.exception.CustomerIsNotFound;
import com.patika.customerservice.exception.NationalIdAndBirthdayIsIncompatibleException;
import com.patika.customerservice.exception.NationalIdIsNullException;
import com.patika.customerservice.mapper.CustomerMapper;
import com.patika.customerservice.model.CustomerDetail;
import com.patika.customerservice.repository.CustomerDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerDetailRepository customerDetailRepository;

    @Override
    public CustomerDetailDTO save(CustomerDetailDTO customerDetailDTO) {

        try{
            if(isCustomerExist(customerDetailDTO.getNationalId())){
                log.error("Customer is already exist with {} national-id",customerDetailDTO.getNationalId());
                throw new CustomerIsExistException("Customer is already exist");
            } else {
                CustomerDetail customerDetail = CustomerMapper.INSTANCE.convertCustomerDTOtoCustomer(customerDetailDTO);
                customerDetail.setCreditScore(calculateCreditScore(customerDetail.getNationalId()));
                customerDetailRepository.save(customerDetail);
                log.info("Customer successfully saved : " + customerDetail);
                return  CustomerMapper.INSTANCE.convertCustomerToCustomerDTO(customerDetail);
            }
        }catch (Exception e){
            throw new RuntimeException("Customer is not saving");
        }
    }

    @Override
    public CustomerDetailDTO update(Long nationalId, CustomerDetailDTO customerDetailDTO) {

        try {
            if(isCustomerExist(nationalId)){
                CustomerDetail customerDetail = customerDetailRepository.findCustomerDetailByNationalId(nationalId).get();
                customerDetail.setIncome(customerDetailDTO.getIncome());
                customerDetail.setPhoneNumber(customerDetailDTO.getPhoneNumber());
                customerDetail.setDepositDetailDTO(customerDetailDTO.getDepositDetailDTO());
                customerDetailRepository.save(customerDetail);
                log.info("Customer successfully updated : " + customerDetail);
                return CustomerMapper.INSTANCE.convertCustomerToCustomerDTO(customerDetail);
            } else {
                log.error("Customer is not found with {} national-id",nationalId);
                throw new CustomerIsNotFound("Customer is not found");
            }
        }catch (Exception e){
            throw new RuntimeException("Customer is not updating.");
        }
    }

    @Override
    public void delete(Long nationalId, LocalDate birthday) {

        try{
            if(isCustomerExist(nationalId)){
                CustomerDetail customerDetail = customerDetailRepository.findCustomerDetailByNationalId(nationalId).get();
                if (customerDetail.getBirthDay().equals(birthday)){
                    customerDetailRepository.delete(customerDetail);
                    log.info("Customer {} successfully deleted from list",nationalId);
                }
            } else {
                log.error("Customer is not found with {} id",nationalId);
                throw new CustomerIsNotFound("Customer is not found");
            }
        }catch (Exception e){
            throw new RuntimeException("Customer is not deleting");
        }
    }

    @Override
    public CustomerDetailDTO findByNationalIdAndBirthday(Long nationalId,LocalDate date) {

        try{
            if(isCustomerExist(nationalId)){
                CustomerDetail customerDetail = customerDetailRepository.findCustomerDetailByNationalId(nationalId).get();
                if(customerDetail.getBirthDay().equals(date)){
                    CustomerDetailDTO customerDetailDTO = CustomerMapper.INSTANCE.convertCustomerToCustomerDTO(customerDetail);
                    log.info("Customer successfully found from list:" + customerDetailDTO);
                    return customerDetailDTO;
                } else {
                    log.error("Customer is incompatible with {} national-id and {} date",nationalId,date);
                    throw new NationalIdAndBirthdayIsIncompatibleException("Customer National Id and Birthday is incompatible");
                }
            } else {
                log.error("Customer is not found with {} national-id",nationalId);
                throw new CustomerIsNotFound("Customer is not found");
            }
        }catch (Exception e){
            throw new RuntimeException("Customer is not finding");
        }
    }

    protected boolean isCustomerExist(Long nationalId){
        return customerDetailRepository.findCustomerDetailByNationalId(nationalId).isPresent();
    }

    protected double calculateCreditScore(Long nationalId){

        if(nationalId == null){
            log.error("Customer is not found with {} national-id",nationalId);
            throw new NationalIdIsNullException("National Id is null");
        }

        long lastDigit = Math.abs(nationalId%10);

        if(lastDigit == 0){
            return 1500;
        }
        else if(lastDigit == 2){
            return lastDigit*500;
        }
        else if(lastDigit == 4){
            return lastDigit*125;
        }
        else if(lastDigit == 6){
            return lastDigit*100;
        }
        else if(lastDigit == 8){
            return lastDigit*50;
        }
        return 0;
    }

}
