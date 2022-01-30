package com.patika.customerservice.mapper;

import com.common.dto.CustomerDetailDTO;
import com.patika.customerservice.model.CustomerDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDetail convertCustomerDTOtoCustomer (CustomerDetailDTO customerDetailDTO);

    CustomerDetailDTO convertCustomerToCustomerDTO(CustomerDetail customerDetail);

}
