package com.lendandborrow.model;

import com.lendandborrow.model.enums.EnumLendingProcessState;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="lendingProcesses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LendingProcess {

    @Id
    @NotNull
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @NotNull
    private User lender;

    @ManyToOne
    @NotNull
    private User borrower;

    @ManyToOne
    @NotNull
    private Article article;

    @Enumerated(EnumType.STRING)
    private EnumLendingProcessState lendingProcessState = EnumLendingProcessState.PENDING;

}
