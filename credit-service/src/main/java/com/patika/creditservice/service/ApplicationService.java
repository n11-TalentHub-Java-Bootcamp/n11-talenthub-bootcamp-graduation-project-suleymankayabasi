package com.patika.creditservice.service;

import com.common.dto.ApplicationDetailDTO;
import com.common.dto.CustomerDetailDTO;

import java.util.List;

public interface ApplicationService {

    ApplicationDetailDTO calculateApplicationDetail(CustomerDetailDTO customerDetailDTO);
    List<ApplicationDetailDTO> findApplicationDetailsByNationalId(Long nationalId);
    void deleteApplicationDetail(Long nationalId);
}
