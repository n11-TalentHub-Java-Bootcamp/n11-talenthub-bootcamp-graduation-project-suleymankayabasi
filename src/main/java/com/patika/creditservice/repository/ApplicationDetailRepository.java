package com.patika.creditservice.repository;

import com.patika.creditservice.model.ApplicationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationDetailRepository extends JpaRepository<ApplicationDetail,Long> {

    Optional<ApplicationDetail> findByNationalId(Long nationalId);
    List<ApplicationDetail> findAllByNationalId(Long nationalId);
    boolean existsApplicationDetailByNationalId(Long id);
}
