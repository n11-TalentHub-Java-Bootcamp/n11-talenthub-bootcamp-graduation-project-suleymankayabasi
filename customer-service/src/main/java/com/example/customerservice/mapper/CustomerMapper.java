package com.example.customerservice.mapper;

import com.example.customerservice.dto.CustomerDTO;
import com.example.customerservice.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer convertCustomerDTOtoCustomer (CustomerDTO customerDTO);

    CustomerDTO convertCustomerToCustomerDTO(Customer customer);

    List<Customer> convertCustomerDTOListToCustomerList(List<CustomerDTO> customerDTOList);

    List<CustomerDTO> convertCustomerListToCustomerDTOList(List<Customer> customerList);
}
