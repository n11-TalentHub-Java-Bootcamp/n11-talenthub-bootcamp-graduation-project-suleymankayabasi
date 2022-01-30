package com.patika.customerservice.mapper;

import com.common.dto.CustomerDetailDTO;
import com.common.dto.DepositDetailDTO;
import com.patika.customerservice.model.CustomerDetail;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    @Test
    void shouldConvertCustomerDTOtoCustomer() {

        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO
                (1L,"test-name","test-surname",1L,1000.0,"1234567890", LocalDate.now(),500.0,
                        new DepositDetailDTO(1L,1L,"test-name",1000.0));

        CustomerDetail customerDetail = CustomerMapper.INSTANCE.convertCustomerDTOtoCustomer(customerDetailDTO);

        assertAll(
                ()->assertEquals(1L,customerDetail.getId()),
                ()->assertEquals("test-name",customerDetail.getName()),
                ()->assertEquals("test-surname",customerDetail.getLastName()),
                ()->assertEquals(1L,customerDetail.getNationalId()),
                ()->assertEquals(1000.0,customerDetail.getIncome()),
                ()->assertEquals("1234567890",customerDetail.getPhoneNumber()),
                ()->assertEquals(LocalDate.now(),customerDetail.getBirthDay()),
                ()->assertEquals(500.0,customerDetail.getCreditScore()),
                ()->assertEquals(1L,customerDetail.getDepositDetailDTO().getId()),
                ()->assertEquals(1L,customerDetail.getDepositDetailDTO().getNationalId()),
                ()->assertEquals("test-name",customerDetail.getDepositDetailDTO().getName()),
                ()->assertEquals(1000.0,customerDetail.getDepositDetailDTO().getAmount())
        );
    }

    @Test
    void shouldConvertCustomerToCustomerDTO() {

        CustomerDetail customerDetail = new CustomerDetail
                (1L,"test-name","test-surname",1L,1000.0,"1234567890", LocalDate.now(),500.0,
                        new DepositDetailDTO(1L,1L,"test-name",1000.0));
        CustomerDetailDTO customerDetailDTO = CustomerMapper.INSTANCE.convertCustomerToCustomerDTO(customerDetail);
        assertAll(
                ()->assertEquals(1L,customerDetailDTO.getId()),
                ()->assertEquals("test-name",customerDetailDTO.getName()),
                ()->assertEquals("test-surname",customerDetailDTO.getLastName()),
                ()->assertEquals(1L,customerDetailDTO.getNationalId()),
                ()->assertEquals(1000.0,customerDetailDTO.getIncome()),
                ()->assertEquals("1234567890",customerDetailDTO.getPhoneNumber()),
                ()->assertEquals(LocalDate.now(),customerDetailDTO.getBirthDay()),
                ()->assertEquals(500.0,customerDetailDTO.getCreditScore()),
                ()->assertEquals(1L,customerDetailDTO.getDepositDetailDTO().getId()),
                ()->assertEquals(1L,customerDetailDTO.getDepositDetailDTO().getNationalId()),
                ()->assertEquals("test-name",customerDetailDTO.getDepositDetailDTO().getName()),
                ()->assertEquals(1000.0,customerDetailDTO.getDepositDetailDTO().getAmount())
        );
    }
}