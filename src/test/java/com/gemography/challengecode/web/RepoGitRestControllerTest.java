package com.gemography.challengecode.web;

import com.gemography.challengecode.dto.RepoDto;
import com.gemography.challengecode.model.Repository;
import com.gemography.challengecode.service.RepositorieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RepoGitRestControllerTest {
    @MockBean
    private RepositorieService repositorieService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("GET /repositories ")
    void testGetNbrRepoUseSameLanguage() throws Exception {
        Map<String, List<Repository>> listMap = new HashMap<>();
        doReturn(listMap).when(repositorieService).getNbrRepoUseSameLanguage();
        mockMvc.perform(get("/api/repositorie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /repositories/statistique ")
    void testGetRepoNbrWithLanguage() throws Exception{
        List<RepoDto>repoDtoList = new ArrayList<>();
        doReturn(repoDtoList).when(repositorieService).getRepoNbrWithLanguage();
        mockMvc.perform(get("/api/repositorie/statistique"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}