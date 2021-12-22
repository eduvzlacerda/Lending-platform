package com.lendandborrow.service;

import com.lendandborrow.model.LendingProcess;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.LendingProcessDTO;
import com.lendandborrow.model.enums.EnumLendingProcessState;
import com.lendandborrow.repositories.LendingProcessRepository;
import com.lendandborrow.utils.converters.LendingProcessConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LendingProcessService {

    private final LendingProcessRepository lendingProcessRepository;


    public LendingProcessDTO acceptLendingProcess(UUID id){
        LendingProcess req = lendingProcessRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        if(req.getLendingProcessState() != EnumLendingProcessState.PENDING ){
            throw new RuntimeException("ProcessState must be pending");
        }
        req.setLendingProcessState(EnumLendingProcessState.ACTIVE);
        return LendingProcessConverter.convertToDTO(lendingProcessRepository.save(req));

    }

    public List<LendingProcessDTO> findAllLendingProcesses() {

        return lendingProcessRepository
                .findAll()
                .stream()
                .map(LendingProcessConverter::convertToDTO)
                .collect(Collectors.toList());

    }

    public List<LendingProcessDTO> findOpenRequestsLender(User lender) {

        return lendingProcessRepository
                .findLendingProcessesByLenderAndLendingProcessState(lender, EnumLendingProcessState.PENDING)
                .stream()
                .map(LendingProcessConverter::convertToDTO)
                .collect(Collectors.toList());

    }




    public LendingProcess addLendingProcess(LendingProcess lendingProcess) {

        lendingProcessRepository.save(lendingProcess);

        return lendingProcess;

    }

}
