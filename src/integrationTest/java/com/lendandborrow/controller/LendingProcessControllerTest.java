package com.lendandborrow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.lendandborrow.CommonIntegrationTest;
import com.lendandborrow.model.LendingProcess;
import com.lendandborrow.model.dto.LendingProcessDTO;
import com.lendandborrow.model.enums.EnumLendingProcessState;
import com.lendandborrow.repositories.LendingProcessRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.Optional;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LendingProcessControllerTest extends CommonIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LendingProcessRepository lendingProcessRepository;

    @Test
    @Sql(scripts = "classpath:/integration.sql")
    void addLendingProcess() throws Exception {

        LendingProcessDTO lendingProcessDTO = LendingProcessDTO
                .builder()
                .lenderId(UUID.fromString("afe19162-047b-473a-9552-0c254cac753b"))
                .borrowerId(UUID.fromString("afe19162-047b-473a-9552-0c254cac753c"))
                .articleId(UUID.fromString("933606a4-506b-4749-ac53-3f07a958a8a7"))
                .lendingProcessState(EnumLendingProcessState.PENDING)
                .build();

        String lendingProcessAsString = objectMapper.writeValueAsString(lendingProcessDTO);

        MvcResult mvcResult = mockMvc.perform(
                post("/lendingProcesses")
                        .contentType("application/json")
                        .content(lendingProcessAsString)
        )
                .andExpect(status().isOk())
                .andReturn();

        String id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");

        Optional<LendingProcess> optionalLendingProcess = lendingProcessRepository.findById(UUID.fromString(id));

        optionalLendingProcess
                .ifPresentOrElse(

                        presentLendingProcess -> {

                            Assertions.assertEquals("Article1", presentLendingProcess.getArticle().getTitle());

                            Assertions.assertEquals("Tester", presentLendingProcess.getLender().getName());
                            Assertions.assertEquals("Tester2", presentLendingProcess.getBorrower().getName());

                            Assertions.assertEquals(EnumLendingProcessState.PENDING, presentLendingProcess.getLendingProcessState());

                        },

                        () -> Assertions.fail("LendingProcess not found"));

    }

    @Test
    @Sql(scripts = "classpath:/integration.sql")
    void acceptLendingProcess() throws Exception {

        String lendingProcessId = "933606a4-506b-4749-ac53-3f07a958a8a7";

        MvcResult mvcResult = mockMvc.perform(
                put("/lendingProcesses/acceptRequest/{id}", lendingProcessId)
        )
                .andExpect(status().isOk())
                .andReturn();

        String id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");
        Optional<LendingProcess> optionalLendingProcess = lendingProcessRepository.findById(UUID.fromString(id));
        optionalLendingProcess.ifPresentOrElse(

                presentLendingProcess -> Assertions.assertEquals(EnumLendingProcessState.ACTIVE, presentLendingProcess.getLendingProcessState()),

                () -> Assertions.fail("LendingProcess did not change process state"));


    }
    @Test
    @Sql(scripts = "classpath:/integration.sql")
    void acceptLendingProcessFailed() throws Exception {

        String lendingProcessId = "933606a4-506b-4749-ac53-3f07a958a8a8";


        MvcResult mvcResult = mockMvc.perform(
                put("/lendingProcesses/acceptRequest/{id}", lendingProcessId))
                .andExpect(status().is5xxServerError())
                .andReturn();

    }

    @Test
    @Sql(scripts = "classpath:/integration.sql")
    void rejectLendingProcess() throws Exception {

        String lendingProcessId = "933606a4-506b-4749-ac53-3f07a958a8a7";

        MvcResult mvcResult = mockMvc.perform(
                put("/lendingProcesses/rejectRequest/{id}", lendingProcessId)
        )
                .andExpect(status().isOk())
                .andReturn();

        String id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");
        Optional<LendingProcess> optionalLendingProcess = lendingProcessRepository.findById(UUID.fromString(id));
        optionalLendingProcess.ifPresentOrElse(

                presentLendingProcess -> Assertions.assertEquals(EnumLendingProcessState.REJECTED, presentLendingProcess.getLendingProcessState()),

                () -> Assertions.fail("LendingProcess did not change process state"));


    }
    @Test
    @Sql(scripts = "classpath:/integration.sql")
    void rejectLendingProcessFailed() throws Exception {

        String lendingProcessId = "933606a4-506b-4749-ac53-3f07a958a8a8";


        MvcResult mvcResult = mockMvc.perform(
                put("/lendingProcesses/rejectRequest/{id}", lendingProcessId))
                .andExpect(status().is5xxServerError())
                .andReturn();

    }


}