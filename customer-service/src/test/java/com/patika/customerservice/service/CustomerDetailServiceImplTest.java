package com.patika.customerservice.service;

import com.common.dto.CustomerDetailDTO;
import com.common.dto.DepositDetailDTO;
import com.patika.customerservice.exception.CustomerIsExistException;
import com.patika.customerservice.exception.CustomerIsNotFound;
import com.patika.customerservice.exception.NationalIdAndBirthdayIsIncompatibleException;
import com.patika.customerservice.mapper.CustomerMapper;
import com.patika.customerservice.model.CustomerDetail;
import com.patika.customerservice.repository.CustomerDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerDetailServiceImplTest {

    @Mock
    CustomerDetailRepository customerDetailRepository;

    @Mock
    CustomerMapper customerMapper;

    @InjectMocks
    CustomerDetailServiceImpl customerDetailServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldSave() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
        customerDetailDTO.setNationalId(2L);
        CustomerDetailDTO test = customerDetailServiceImpl.save(customerDetailDTO);
        assertEquals(customerDetailDTO.getNationalId(),test.getNationalId());
    }

    @Test
    void shouldNotSaveIfExist() {

        CustomerDetail customerDetail = new CustomerDetail();
        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
        customerDetailDTO.setNationalId(2L);
        when(customerDetailRepository.findCustomerDetailByNationalId(2L)).thenReturn(Optional.of(customerDetail));
        when(customerMapper.convertCustomerToCustomerDTO(customerDetail)).thenReturn(customerDetailDTO);
        assertThrows(CustomerIsExistException.class, () -> customerDetailServiceImpl.save(customerDetailDTO));
    }

    @Test
    void shouldNotUpdateIfNot() {
        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
        assertThrows(CustomerIsNotFound.class, () -> customerDetailServiceImpl.update(2L,customerDetailDTO));
    }

    @Test
    void shouldUpdate() {
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setNationalId(2L);
        when(customerDetailRepository.save(customerDetail)).thenReturn(customerDetail);
        when(customerDetailRepository.findCustomerDetailByNationalId(anyLong())).thenReturn(Optional.of(customerDetail));
        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
        customerDetailDTO.setIncome(100.0);
        CustomerDetailDTO test = customerDetailServiceImpl.update(2L,customerDetailDTO);
        assertEquals(test.getIncome(),customerDetailDTO.getIncome());
    }

    @Test
    void shouldFindByNationalIdAndBirthday() {
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setNationalId(2L);
        customerDetail.setBirthDay(LocalDate.now());
        when(customerDetailRepository.save(customerDetail)).thenReturn(customerDetail);
        when(customerDetailRepository.findCustomerDetailByNationalId(anyLong())).thenReturn(Optional.of(customerDetail));
        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
        customerDetailDTO.setNationalId(2L);
        customerDetailDTO.setBirthDay(LocalDate.now());
        CustomerDetailDTO test  = customerDetailServiceImpl.findByNationalIdAndBirthday(customerDetailDTO.getNationalId(),LocalDate.now());
        assertEquals(test.getBirthDay(),customerDetailDTO.getBirthDay());
    }

    @Test
    void shouldNotFindByNationalIdAndBirthday() {
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setNationalId(2L);
        customerDetail.setBirthDay(LocalDate.now());
        when(customerDetailRepository.save(customerDetail)).thenReturn(customerDetail);
        when(customerDetailRepository.findCustomerDetailByNationalId(anyLong())).thenReturn(Optional.of(customerDetail));
        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
        customerDetailDTO.setNationalId(2L);
        assertThrows(NationalIdAndBirthdayIsIncompatibleException.class,() -> customerDetailServiceImpl.findByNationalIdAndBirthday(customerDetailDTO.getNationalId(),LocalDate.now()));
    }

    @Test
    void shouldNotFindByNationalId(){

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
        customerDetailDTO.setNationalId(1L);
        customerDetailDTO.setBirthDay(LocalDate.now());
        assertThrows(CustomerIsNotFound.class,() ->customerDetailServiceImpl.findByNationalIdAndBirthday(anyLong(), LocalDate.of(2000,01,01)));
    }

    @Test
    void shouldNotDeleteIfNot() {
        assertThrows(CustomerIsNotFound.class, () -> customerDetailServiceImpl.delete(anyLong(),LocalDate.now()));
    }

    @Test
    void  shouldDelete() {
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setNationalId(2L);
        customerDetail.setBirthDay(LocalDate.now());
        when(customerDetailRepository.findCustomerDetailByNationalId(anyLong())).thenReturn(Optional.of(customerDetail));
        customerDetailServiceImpl.delete(2L,LocalDate.now());
    }

    @Test
    void isCustomerExist() {
        CustomerDetail customerDetail = new CustomerDetail(1L, "name", "lastName", 2L, 0.0, "phoneNumber", LocalDate.now(), 0.0,
                new DepositDetailDTO(1L, 2L, "name",  0.0));
        Optional<CustomerDetail> optionalCustomerDetail = Optional.of(customerDetail);
        when(customerDetailRepository.findCustomerDetailByNationalId(2L)).thenReturn(optionalCustomerDetail);
        boolean result = customerDetailServiceImpl.isCustomerExist(2L);
        assertTrue(result);
    }

    @Test
    void shouldCalculateCreditScoreIfLastDigit0() {
        Long nationalId = 1234567890L;
        assertEquals(1500,customerDetailServiceImpl.calculateCreditScore(nationalId));
    }

    @Test
    void shouldCalculateCreditScoreIfLastDigit2() {
        Long nationalId = 1234567892L;
        assertEquals(1000,customerDetailServiceImpl.calculateCreditScore(nationalId));
    }

    @Test
    void shouldCalculateCreditScoreIfLastDigit4() {
        Long nationalId = 1234567894L;
        assertEquals(500,customerDetailServiceImpl.calculateCreditScore(nationalId));
    }

    @Test
    void shouldCalculateCreditScoreIfLastDigit6() {
        Long nationalId = 1234567896L;
        assertEquals(600,customerDetailServiceImpl.calculateCreditScore(nationalId));
    }

    @Test
    void shouldCalculateCreditScoreIfLastDigit8() {
        Long nationalId = 1234567898L;
        assertEquals(400,customerDetailServiceImpl.calculateCreditScore(nationalId));
    }
}
