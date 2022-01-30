package com.patika.creditservice.mapper;

import com.common.dto.ApplicationDetailDTO;
import com.patika.creditservice.model.ApplicationDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-30T15:45:20+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class ApplicationMapperImpl implements ApplicationMapper {

    @Override
    public ApplicationDetailDTO convertApplicationDetailToApplicationDetailDTO(ApplicationDetail applicationDetail) {
        if ( applicationDetail == null ) {
            return null;
        }

        ApplicationDetailDTO applicationDetailDTO = new ApplicationDetailDTO();

        applicationDetailDTO.setId( applicationDetail.getId() );
        applicationDetailDTO.setNationalId( applicationDetail.getNationalId() );
        applicationDetailDTO.setCreatedAt( applicationDetail.getCreatedAt() );
        applicationDetailDTO.setApprovalStatus( applicationDetail.isApprovalStatus() );
        applicationDetailDTO.setCreditLimit( applicationDetail.getCreditLimit() );

        return applicationDetailDTO;
    }

    @Override
    public List<ApplicationDetailDTO> convertApplicationDetailListToApplicationDetailDTOList(List<ApplicationDetail> applicationDetailList) {
        if ( applicationDetailList == null ) {
            return null;
        }

        List<ApplicationDetailDTO> list = new ArrayList<ApplicationDetailDTO>( applicationDetailList.size() );
        for ( ApplicationDetail applicationDetail : applicationDetailList ) {
            list.add( convertApplicationDetailToApplicationDetailDTO( applicationDetail ) );
        }

        return list;
    }
}
