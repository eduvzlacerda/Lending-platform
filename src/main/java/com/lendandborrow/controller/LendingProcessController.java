package com.lendandborrow.controller;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.LendingProcess;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.LendingProcessDTO;
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
    public ResponseEntity<List<LendingProcessDTO>> getLendingProcesses() {
        return ok(lendingProcessService.findAllLendingProcesses());
    }

    @GetMapping("/openRequestsLender")
    public ResponseEntity<List<LendingProcessDTO>> getOpenRequestsLender(@RequestParam UUID userId) {

        User lender = userService.getUser(userId);

        if(lender == null){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return ok(lendingProcessService.findOpenRequestsLender(lender));

    }

    @PostMapping
    public ResponseEntity<LendingProcessDTO> addLendingProcess(@RequestBody LendingProcessDTO lendingProcessDTO) {

        User lender = userService.getUser(lendingProcessDTO.getLenderId());
        User borrower = userService.getUser(lendingProcessDTO.getBorrowerId());
        Article article = articleService.getArticle(lendingProcessDTO.getArticleId());

        LendingProcess lendingProcess = LendingProcessConverter.convertFromDTO(lendingProcessDTO, lender, borrower, article);

        return ok(LendingProcessConverter.convertToDTO(lendingProcessService.addLendingProcess(lendingProcess)));

    }



}
