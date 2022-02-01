package com.lendandborrow.model.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LendingProcessRequestDTO {

    @NotNull
    private UUID id = UUID.randomUUID();

    @NotNull
    private String borrowerName;

    @NotNull
    private String articleName;

    @NotNull
    private String lendingProcessState;

}
