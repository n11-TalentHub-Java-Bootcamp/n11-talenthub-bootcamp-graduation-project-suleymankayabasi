package com.example.customerservice.service;

import com.example.customerservice.dto.DepositDTO;
import com.example.customerservice.enums.ErrorMessage;
import com.example.customerservice.exception.DepositIsAlreadyExistException;
import com.example.customerservice.exception.DepositNotFoundException;
import com.example.customerservice.mapper.DepositMapper;
import com.example.customerservice.model.Deposit;
import com.example.customerservice.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;

    Logger logger = LoggerFactory.getLogger(DepositServiceImpl.class);

    @Override
    public List<DepositDTO> findAllDeposit() {
        logger.info("Deposit Service finding process is started");
        List<Deposit> depositList = depositRepository.findAll();
        if(depositList.isEmpty()){
            logger.error("Deposit service finding process error : " + ErrorMessage.DEPOSIT_IS_NOT_FOUND);
            throw new DepositNotFoundException(ErrorMessage.DEPOSIT_IS_NOT_FOUND.toString());
        }
        List<DepositDTO>  depositDTOList = DepositMapper.INSTANCE.convertDepositListToDepositDTOList(depositList);
        logger.info("Deposit Service finding process is done successfully : " + depositDTOList.toString());
        return depositDTOList;
    }

    @Override
    public List<DepositDTO> findAllDepositByCustomerId(Long id) {
        logger.info("Deposit Service finding process is started");
        List<Deposit> depositList = depositRepository.findAllByCustomer_Id(id);
        if(depositList.isEmpty()){
            logger.error("Deposit service finding process error : " + ErrorMessage.DEPOSIT_IS_NOT_FOUND);
            throw new DepositNotFoundException(ErrorMessage.DEPOSIT_IS_NOT_FOUND.toString());
        }
        List<DepositDTO>  depositDTOList = DepositMapper.INSTANCE.convertDepositListToDepositDTOList(depositList);
        logger.info("Deposit Service finding process is done successfully : " + depositDTOList.toString());
        return depositDTOList;
    }

    @Override
    public DepositDTO save(DepositDTO depositDTO) {
        logger.info("Deposit Service saving process is started");
        boolean isExist = depositRepository.existsById(depositDTO.getId());
        if(isExist){
            logger.error("Deposit service saving process error : " + ErrorMessage.DEPOSIT_IS_ALREADY_EXIST);
            throw new DepositIsAlreadyExistException(ErrorMessage.DEPOSIT_IS_ALREADY_EXIST.toString());
        }
        Deposit deposit = DepositMapper.INSTANCE.convertDepositDTOtoDeposit(depositDTO);
        deposit = depositRepository.save(deposit);
        DepositDTO depositDTOResponse = DepositMapper.INSTANCE.convertDepositToDepositDTO(deposit);
        logger.info("Deposit Service saving process is done successfully : " + depositDTOResponse.toString());
        return depositDTOResponse;
    }

    @Override
    public DepositDTO update(Long id,DepositDTO depositDTO) {
        logger.info("Deposit Service updating process is started");
        if(depositRepository.findById(id).isPresent()){
            Deposit deposit = depositRepository.findById(id).get();
            deposit.setDepositAmount(depositDTO.getDepositAmount());
            deposit.setDate(LocalDate.now());
            deposit.setName(depositDTO.getName());
            deposit = depositRepository.save(deposit);
            DepositDTO depositDTOResponse = DepositMapper.INSTANCE.convertDepositToDepositDTO(deposit);
            logger.info("Deposit Service update process is done successfully : " + depositDTOResponse.toString());
            return depositDTOResponse;
        }
        else {
            logger.error("Deposit service updating process error : " + ErrorMessage.DEPOSIT_IS_NOT_FOUND);
            throw new DepositNotFoundException(ErrorMessage.DEPOSIT_IS_NOT_FOUND.toString());
        }
    }

    @Override
    public void delete(Long id) {
        logger.info("Deposit Service deleting process is started");
        Optional<Deposit> deposit=depositRepository.findById(id);
        if(!deposit.isPresent()){
            logger.error("Deposit service deleting process error : " + ErrorMessage.DEPOSIT_IS_NOT_FOUND);
            throw new DepositNotFoundException(ErrorMessage.DEPOSIT_IS_NOT_FOUND.toString());
        }
        logger.info("Deposit Service deleting process is done successfully");
        depositRepository.deleteById(id);
    }
}
