package com.lendandborrow.repositories;

import com.lendandborrow.model.LendingProcess;
import com.lendandborrow.model.User;
import com.lendandborrow.model.enums.EnumLendingProcessState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LendingProcessRepository extends JpaRepository<LendingProcess, UUID> {

    List<LendingProcess> findLendingProcessesByLenderAndLendingProcessState(User lender, EnumLendingProcessState lendingProcessState);

}
