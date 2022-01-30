package com.patika.creditservice.service;

import com.common.dto.ApplicationDetailDTO;
import com.common.dto.CustomerDetailDTO;
import com.patika.creditservice.enums.CreditLimitFactor;
import com.patika.creditservice.exception.ApplicationDetailNotFoundException;
import com.patika.creditservice.mapper.ApplicationMapper;
import com.patika.creditservice.model.ApplicationDetail;
import com.patika.creditservice.repository.ApplicationDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    private final ApplicationDetailRepository applicationDetailRepository;

    @Override
    public ApplicationDetailDTO calculateApplicationDetail(CustomerDetailDTO customerDetailDTO) {

        try{
            double creditLimit = calculateCreditLimit(customerDetailDTO);
            boolean status = creditLimit > 0;
            ApplicationDetail applicationDetail = new ApplicationDetail();
            applicationDetail.setNationalId(customerDetailDTO.getNationalId());
            applicationDetail.setCreditLimit(creditLimit);
            applicationDetail.setApprovalStatus(status);
            applicationDetail.setCreatedAt(LocalDate.now());
            applicationDetail.setCreditScore(customerDetailDTO.getCreditScore());
            applicationDetailRepository.save(applicationDetail);
            log.info("Application Detail calculate process is done successfully : " + applicationDetail);
            return ApplicationMapper.INSTANCE.convertApplicationDetailToApplicationDetailDTO(applicationDetail);
        } catch (Exception e){
            throw new RuntimeException("Application Detail is not calculating.");
        }
    }

    @Override
    public List<ApplicationDetailDTO> findApplicationDetailsByNationalId(Long nationalId) {

        try{
            List<ApplicationDetail> applicationDetailList = applicationDetailRepository.findAllByNationalId(nationalId);
            if(applicationDetailList.isEmpty()){
                log.error("Application Detail list is empty");
                throw new ApplicationDetailNotFoundException("Application is not found");
            } else {
                log.info("Application Detail successfully found from list : " + applicationDetailList);
                return ApplicationMapper.INSTANCE.convertApplicationDetailListToApplicationDetailDTOList(applicationDetailList);
            }
        } catch (Exception e){
            throw new ApplicationDetailNotFoundException("Application is not found");
        }
    }

    @Override
    public void deleteApplicationDetail(Long nationalId) {

        try{
            if(applicationDetailRepository.existsApplicationDetailByNationalId(nationalId)){
                ApplicationDetail applicationDetail = applicationDetailRepository.findByNationalId(nationalId).get();
                log.info("Application {} successfully deleted from list",nationalId);
                applicationDetailRepository.delete(applicationDetail);
            } else {
                log.error("Application is not found with {} id",nationalId);
                throw new ApplicationDetailNotFoundException("Application is not found ") ;
            }
        }catch (Exception e){
            throw new ApplicationDetailNotFoundException("Application is not found ") ;
        }
    }

    protected double calculateCreditLimit(CustomerDetailDTO customerDetailDTO){

        Double creditScore = 0.0;
        if(customerDetailDTO.getCreditScore() != null){
            creditScore = customerDetailDTO.getCreditScore();
        } else {
            log.error("Credit Score is not found");
        }

        Double income = 0.0;
        if(customerDetailDTO.getIncome() != null){
            income = customerDetailDTO.getIncome();
        } else {
            log.error("Income is not found");
        }

        Double deposit = 0.0;
        if(customerDetailDTO.getDepositDetailDTO().getAmount() != null){
            deposit = customerDetailDTO.getDepositDetailDTO().getAmount();
        }else {
            log.error("Deposit amount is not found");
        }


        double creditLimitResult =  0;

        if(creditScore < 500){
            return creditLimitResult;
        }
        else if(creditScore >= 500 &&  creditScore < 1000 && income < 5000 ){
            if(deposit > 0){
                creditLimitResult += deposit * 0.1;
                return 10000 + creditLimitResult ;
            }
            return 10000;
        }
        else if(creditScore >= 500 &&  creditScore < 1000 && income >= 5000  && income < 10000){
            if(deposit > 0){
                creditLimitResult += deposit * 0.2;
                return creditLimitResult + 20000;
            }
            return 20000;
        }
        else if(creditScore >= 500 &&  creditScore < 1000 && income >= 10000){
            if(deposit > 0){
                creditLimitResult += deposit * 0.25;
                creditLimitResult += (income * CreditLimitFactor.CREDIT_LIMIT_FACTOR.getFactor() /2);
                return creditLimitResult;
            }
            return creditLimitResult + (income * CreditLimitFactor.CREDIT_LIMIT_FACTOR.getFactor() /2);
        }
        else if(creditScore >= 1000){
            if(deposit > 0){
                deposit = deposit * 0.50;
                creditLimitResult += deposit + (income * CreditLimitFactor.CREDIT_LIMIT_FACTOR.getFactor());
                return creditLimitResult;
            }
            creditLimitResult += (income * CreditLimitFactor.CREDIT_LIMIT_FACTOR.getFactor());
            return creditLimitResult;
        }
        return creditLimitResult;
    }
}
