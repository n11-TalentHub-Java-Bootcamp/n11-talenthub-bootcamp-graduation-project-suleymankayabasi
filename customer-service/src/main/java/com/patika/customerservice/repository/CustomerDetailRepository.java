package com.patika.customerservice.repository;

import com.patika.customerservice.model.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail,Long> {

    Optional<CustomerDetail> findCustomerDetailByNationalId(Long id);
}
