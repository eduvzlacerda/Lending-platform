package com.lendandborrow.model.dto;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.User;
import com.lendandborrow.model.enums.EnumLendingProcessState;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LendingProcessDTO {

    @NotNull
    private UUID id = UUID.randomUUID();

    @NotNull
    private UUID lenderId;

    @NotNull
    private UUID borrowerId;

    @NotNull
    private UUID articleId;

    private EnumLendingProcessState lendingProcessState = EnumLendingProcessState.PENDING;

}
