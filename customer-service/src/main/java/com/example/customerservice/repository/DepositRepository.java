package com.example.customerservice.repository;

import com.example.customerservice.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepositRepository extends JpaRepository<Deposit,Long> {

    List<Deposit> findAllByCustomer_Id(Long id);

}
