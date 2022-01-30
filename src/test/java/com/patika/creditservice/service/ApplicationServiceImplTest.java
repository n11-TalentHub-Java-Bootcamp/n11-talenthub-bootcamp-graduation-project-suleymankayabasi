package com.patika.creditservice.service;

import com.common.dto.ApplicationDetailDTO;
import com.common.dto.CustomerDetailDTO;
import com.common.dto.DepositDetailDTO;
import com.patika.creditservice.exception.ApplicationDetailNotFoundException;
import com.patika.creditservice.model.ApplicationDetail;
import com.patika.creditservice.repository.ApplicationDetailRepository;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationServiceImplTest {

    @Mock
    ApplicationDetailRepository applicationDetailRepository;

    @InjectMocks
    ApplicationServiceImpl applicationServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldCalculateApplicationDetail() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L, "name", "lastName", 2L, 1000.0, "phoneNumber", LocalDate.now(), 500.0,
                new DepositDetailDTO(1L, 2L, "name",  0.0));
        ApplicationDetailDTO result = applicationServiceImpl.calculateApplicationDetail(customerDetailDTO);
        assertEquals(new ApplicationDetailDTO(1L, 2L, LocalDate.now(), true, 10000.0).getCreditLimit(),result.getCreditLimit());
    }

    @Test
    void shouldFindApplicationDetailsByNationalId() {
        when(applicationDetailRepository.findAllByNationalId(anyLong())).thenReturn(Arrays.<ApplicationDetail>asList(new ApplicationDetail(1L, 1L, LocalDate.now(), false,  0.0,  0.0)));
        List<ApplicationDetailDTO> result = applicationServiceImpl.findApplicationDetailsByNationalId(1L);
        assertEquals(List.<ApplicationDetailDTO>of(new ApplicationDetailDTO(1L, 1L, LocalDate.now(), false,  0.0)), result);
    }

    @Test
    void shouldNotFindIfNotApplicationDetailsByNationalId() {
        assertThrows(ApplicationDetailNotFoundException.class, () -> applicationServiceImpl.findApplicationDetailsByNationalId(null));
    }

    @Test
    void shouldDeleteApplicationDetail() {
        ApplicationDetail applicationDetail = new ApplicationDetail(1L,1L,LocalDate.now(),false,0.0,499.0);
        when(applicationDetailRepository.existsApplicationDetailByNationalId(anyLong())).thenReturn(true);
        when(applicationDetailRepository.findByNationalId(1L)).thenReturn(Optional.of(applicationDetail));
        applicationServiceImpl.deleteApplicationDetail(1L);
    }

    @Test
    void shouldNotDeleteIfNotApplicationDetail() {
        when(applicationDetailRepository.existsApplicationDetailByNationalId(anyLong())).thenReturn(false);
        assertThrows(ApplicationDetailNotFoundException.class, () -> applicationServiceImpl.deleteApplicationDetail(anyLong()));
    }
    @Test
    void shouldCalculateCreditLimitBeforeCheckDepositDetail() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,1000.0,"1234567890", LocalDate.now(),0.0,
                new DepositDetailDTO(1L,1L,"test-name",0.0));

        assertNotEquals(null,customerDetailDTO.getDepositDetailDTO());
    }

    @Test
    void shouldCalculateCreditLimitIfCreditScoreIsSmallerThan500() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,1000.0,"1234567890", LocalDate.now(),0.0,
                new DepositDetailDTO(1L,1L,"test-name",0.0));

        assertEquals(0.0,applicationServiceImpl.calculateCreditLimit(customerDetailDTO));
    }

    @Test
    void shouldCalculateCreditLimitIfCreditScoreIsBiggerThan500AndIncomeSmallerThan5000AndIfNoDeposit() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,1000.0,"1234567890", LocalDate.now(),500.0,
                new DepositDetailDTO(1L,1L,"test-name",0.0));

        assertEquals(10000.0,applicationServiceImpl.calculateCreditLimit(customerDetailDTO));
    }

    @Test
    void shouldCalculateCreditLimitIfCreditScoreIsBiggerThan500AndIncomeSmallerThan500() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,1000.0,"1234567890", LocalDate.now(),500.0,
                new DepositDetailDTO(1L,1L,"test-name",100.0));

        assertEquals(10010.0,applicationServiceImpl.calculateCreditLimit(customerDetailDTO));
    }

    @Test
    void shouldCalculateCreditLimitIfCreditScoreIsBiggerThan500AndIncomeBiggerThan5000AndIfNoDeposit() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,5000.0,"1234567890", LocalDate.now(),700.0,
                new DepositDetailDTO(1L,1L,"test-name",0.0));

        assertEquals(20000.0,applicationServiceImpl.calculateCreditLimit(customerDetailDTO));
    }

    @Test
    void shouldCalculateCreditLimitIfCreditScoreIsBiggerThan500AndIncomeBiggerThan5000() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,6000.0,"1234567890", LocalDate.now(),800.0,
                new DepositDetailDTO(1L,1L,"test-name",100.0));

        assertEquals(20020.0,applicationServiceImpl.calculateCreditLimit(customerDetailDTO));
    }

    @Test
    void shouldCalculateCreditLimitIfCreditScoreIsBiggerThan500AndIncomeBiggerThan10000AndIfNoDeposit() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,15000.0,"1234567890", LocalDate.now(),900.0,
                new DepositDetailDTO(1L,1L,"test-name",0.0));

        assertEquals(30000.0,applicationServiceImpl.calculateCreditLimit(customerDetailDTO));
    }

    @Test
    void shouldCalculateCreditLimitIfCreditScoreIsBiggerThan500AndIncomeBiggerThan10000And() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,15000.0,"1234567890", LocalDate.now(),900.0,
                new DepositDetailDTO(1L,1L,"test-name",100.0));

        assertEquals(30025.0,applicationServiceImpl.calculateCreditLimit(customerDetailDTO));
    }

    @Test
    void shouldCalculateCreditLimitIfCreditScoreIsBiggerThan1000AndIfNoDeposit() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,20000.0,"1234567890", LocalDate.now(),1000.0,
                new DepositDetailDTO(1L,1L,"test-name",0.0));

        assertEquals(80000.0,applicationServiceImpl.calculateCreditLimit(customerDetailDTO));
    }

    @Test
    void shouldCalculateCreditLimitIfCreditScoreIsBiggerThan1000() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(1L,"test-name","test-surname",
                1L,20000.0,"1234567890", LocalDate.now(),1000.0,
                new DepositDetailDTO(1L,1L,"test-name",100.0));

        assertEquals(80050.0,applicationServiceImpl.calculateCreditLimit(customerDetailDTO));
    }
}
