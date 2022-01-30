package com.patika.creditservice.mapper;

import com.common.dto.ApplicationDetailDTO;
import com.patika.creditservice.model.ApplicationDetail;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationMapperTest {

    @Test
    void shouldConvertApplicationDetailToApplicationDetailDTO() {
        ApplicationDetail applicationDetail = new ApplicationDetail(1L,1L, LocalDate.now(),true,1000.0,500.0);
        ApplicationDetailDTO applicationDetailDTO = ApplicationMapper.INSTANCE.convertApplicationDetailToApplicationDetailDTO(applicationDetail);
        assertAll(
                () -> assertEquals(1L,applicationDetailDTO.getId()),
                () -> assertEquals(1L,applicationDetailDTO.getNationalId()),
                () -> assertEquals(LocalDate.now(),applicationDetailDTO.getCreatedAt()),
                () -> assertTrue(applicationDetailDTO.isApprovalStatus()),
                () -> assertEquals(1000.0,applicationDetailDTO.getCreditLimit())
        );
    }

    @Test
    void shouldConvertApplicationDetailListToApplicationDetailDTOList() {
        List<ApplicationDetail> applicationDetailList = new ArrayList<ApplicationDetail>();
        applicationDetailList.add(new ApplicationDetail(1L,1L, LocalDate.now(),true,1000.0,500.0));
        List<ApplicationDetailDTO> applicationDetailDTOList = ApplicationMapper.INSTANCE.convertApplicationDetailListToApplicationDetailDTOList(applicationDetailList);
        applicationDetailList.get(0);

        assertAll(
                () -> assertEquals(applicationDetailList.get(0).getId(),applicationDetailDTOList.get(0).getId()),
                () -> assertEquals(applicationDetailList.get(0).getNationalId(),applicationDetailDTOList.get(0).getNationalId()),
                () -> assertEquals(applicationDetailList.get(0).getCreatedAt(),applicationDetailDTOList.get(0).getCreatedAt()),
                () -> assertEquals(applicationDetailList.get(0).isApprovalStatus(),applicationDetailDTOList.get(0).isApprovalStatus()),
                () -> assertEquals(applicationDetailList.get(0).getCreditLimit(),applicationDetailDTOList.get(0).getCreditLimit())
        );
    }
}