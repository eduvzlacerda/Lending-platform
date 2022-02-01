package com.lendandborrow.controller;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.LendingProcess;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.LendingProcessDTO;

import com.lendandborrow.model.dto.LendingProcessRequestDTO;
import com.lendandborrow.model.enums.EnumLendingProcessState;
import com.lendandborrow.service.ArticleService;
import com.lendandborrow.service.LendingProcessService;
import com.lendandborrow.service.UserService;
import com.lendandborrow.utils.converters.LendingProcessConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/lendingProcesses")
public class LendingProcessController {

    private final LendingProcessService lendingProcessService;

    private final UserService userService;

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<LendingProcessRequestDTO>> getLendingProcesses() {
        return ok(lendingProcessService.findAllLendingProcesses());
    }

    @PostMapping("/rejectRequest/")
    public ResponseEntity<LendingProcessRequestDTO> rejectLendingRequest(@RequestParam UUID id){
        return ok(lendingProcessService.rejectLendingProcess(id));
    }

    @PostMapping("/acceptRequest")
    public ResponseEntity<LendingProcessRequestDTO> acceptLendingRequest(@RequestParam("id") UUID id){
       return ok(lendingProcessService.acceptLendingProcess(id));
    }

    @GetMapping("/openRequestsLender/{userId}")
    public ResponseEntity<List<LendingProcessRequestDTO>> getOpenRequestsLender(@PathVariable UUID userId) {
        User lender = userService.getUser(userId);

        if(lender == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ok(lendingProcessService.findOpenRequestsLender(lender));
    }

    @GetMapping("/requestsBorrower/{userId}")
    public ResponseEntity<List<LendingProcessRequestDTO>> getRequestsBorrower(@PathVariable UUID userId) {
        User borrower = userService.getUser(userId);

        if(borrower == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ok(lendingProcessService.findRequestsBorrower(borrower));
    }

    @GetMapping("/processedRequestsLender/{userId}")
    public ResponseEntity<List<LendingProcessRequestDTO>> getProcessedRequestsLender(@PathVariable UUID userId) {

        User lender = userService.getUser(userId);

        if(lender == null){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return ok(lendingProcessService.findProcessedRequestsLender(lender));

    }

    @PostMapping
    public ResponseEntity<LendingProcessRequestDTO> addLendingProcess(@RequestBody LendingProcessDTO lendingProcessDTO) {

        User lender = userService.getUser(lendingProcessDTO.getLenderId());
        User borrower = userService.getUser(lendingProcessDTO.getBorrowerId());
        Article article = articleService.getArticle(lendingProcessDTO.getArticleId());

        LendingProcess lendingProcess = LendingProcessConverter.convertFromDTO(lendingProcessDTO, lender, borrower, article);

        return ok(LendingProcessConverter.convertToDTO(lendingProcessService.addLendingProcess(lendingProcess)));

    }

    @PutMapping("giveBackArticle/{lendingProcessId}")
    public ResponseEntity<LendingProcessRequestDTO> giveBackArticle(@PathVariable UUID lendingProcessId){
       return ok(lendingProcessService.giveBackArticle(lendingProcessId));
    }



}
