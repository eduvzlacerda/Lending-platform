package com.lendandborrow.utils.converters;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.LendingProcess;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.LendingProcessDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LendingProcessConverter {

    public static LendingProcessDTO convertToDTO(LendingProcess lendingProcess) {
        return LendingProcessDTO.builder()
                .id(lendingProcess.getId())
                .lenderId(lendingProcess.getLender().getId())
                .borrowerId(lendingProcess.getBorrower().getId())
                .articleId(lendingProcess.getArticle().getId())
                .lendingProcessState(lendingProcess.getLendingProcessState())
                .build();
    }

    public static LendingProcess convertFromDTO(
            LendingProcessDTO lendingProcessDTO,
            User lender,
            User borrower,
            Article article) {

        return LendingProcess.builder()
                .id(lendingProcessDTO.getId())
                .lender(lender)
                .borrower(borrower)
                .article(article)
                .lendingProcessState(lendingProcessDTO.getLendingProcessState())
                .build();

    }

}
