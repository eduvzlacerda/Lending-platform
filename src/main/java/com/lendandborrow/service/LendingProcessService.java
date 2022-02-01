package com.lendandborrow.service;

import com.lendandborrow.ExcepetionHandling.exceptions.LendingProcessServiceException;
import com.lendandborrow.model.Article;
import com.lendandborrow.model.LendingProcess;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.LendingProcessDTO;
import com.lendandborrow.model.enums.EnumLendingProcessState;
import com.lendandborrow.repositories.LendingProcessRepository;
import com.lendandborrow.utils.converters.LendingProcessConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LendingProcessService {

    private final LendingProcessRepository lendingProcessRepository;


    public LendingProcessRequestDTO rejectLendingProcess(UUID id){
        LendingProcess req = lendingProcessRepository.findById(id).orElseThrow(()-> new LendingProcessServiceException("Entity not found"));
        if(req.getLendingProcessState() != EnumLendingProcessState.PENDING ){
            throw new LendingProcessServiceException("ProcessState must be pending");
        }
        req.setLendingProcessState(EnumLendingProcessState.REJECTED);
        return LendingProcessConverter.convertToDTO(lendingProcessRepository.save(req));

    }


    public LendingProcessRequestDTO acceptLendingProcess(UUID id){
        LendingProcess req = lendingProcessRepository.findById(id).orElseThrow(()-> new LendingProcessServiceException("Entity not found"));
        if(req.getLendingProcessState() != EnumLendingProcessState.PENDING ){
            throw new LendingProcessServiceException("ProcessState must be pending");
        }
        req.setLendingProcessState(EnumLendingProcessState.ACTIVE);
        return LendingProcessConverter.convertToDTO(lendingProcessRepository.save(req));

    }
    public LendingProcessRequestDTO giveBackArticle(UUID id){
        LendingProcess req = lendingProcessRepository.findById(id).orElseThrow(()-> new LendingProcessServiceException("Entity not found"));

        if(req.getLendingProcessState() != EnumLendingProcessState.ACTIVE){
            throw new LendingProcessServiceException("ProcessState must be Active to return article");
        }
        req.setLendingProcessState(EnumLendingProcessState.FINISHED);

        return LendingProcessConverter.convertToDTO(lendingProcessRepository.save(req));

    }



    public List<LendingProcessRequestDTO> findAllLendingProcesses() {

        return lendingProcessRepository
                .findAll()
                .stream()
                .map(LendingProcessConverter::convertToDTO)
                .collect(Collectors.toList());

    }

    public List<LendingProcessRequestDTO> findOpenRequestsLender(User lender) {

        return lendingProcessRepository
                .findLendingProcessesByLenderAndLendingProcessState(lender, EnumLendingProcessState.PENDING)
                .stream()
                .map(LendingProcessConverter::convertToDTO)
                .collect(Collectors.toList());

    }

    public List<LendingProcessRequestDTO> findProcessedRequestsLender(User lender) {

        return lendingProcessRepository
                .findLendingProcessesByLenderAndLendingProcessStateNot(lender, EnumLendingProcessState.PENDING)
                .stream()
                .map(LendingProcessConverter::convertToDTO)
                .collect(Collectors.toList());

    }

    public LendingProcess addLendingProcess(User borrower, Article article) {

        if(!article.getArticleStatus().equals(EnumArticleStatus.AVAILABLE)) {
            throw new LendingProcessServiceException("Article must be in state '" + EnumArticleStatus.AVAILABLE + "'!");
        }

        LendingProcess lendingProcess = new LendingProcess();

        lendingProcess.setLendingProcessState(EnumLendingProcessState.PENDING);
        lendingProcess.setBorrower(borrower);
        lendingProcess.setArticle(article);
        lendingProcess.setLender(article.getOwner()); // TODO: Do we really have to save the lender in this db-relation?

        lendingProcessRepository.save(lendingProcess);

        return lendingProcess;

    }


    public LendingProcess addLendingProcess(LendingProcess lendingProcess) {

        if(lendingProcess.getLender() != lendingProcess.getArticle().getOwner()){
            throw new LendingProcessServiceException("Lender must own the Article");
        }

        lendingProcessRepository.save(lendingProcess);

        return lendingProcess;

    }

    public List<LendingProcessRequestDTO> findRequestsBorrower(User borrower) {

        return lendingProcessRepository
                .findLendingProcessesByBorrower(borrower)
                .stream()
                .map(LendingProcessConverter::convertToDTO)
                .collect(Collectors.toList());

    }
}
