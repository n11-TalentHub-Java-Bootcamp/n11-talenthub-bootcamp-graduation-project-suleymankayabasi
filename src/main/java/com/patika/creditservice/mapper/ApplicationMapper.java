package com.patika.creditservice.mapper;

import com.common.dto.ApplicationDetailDTO;
import com.patika.creditservice.model.ApplicationDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    ApplicationDetailDTO convertApplicationDetailToApplicationDetailDTO(ApplicationDetail applicationDetail);

    List<ApplicationDetailDTO> convertApplicationDetailListToApplicationDetailDTOList(List<ApplicationDetail> applicationDetailList);

}
