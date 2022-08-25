package com.amr.project.webapp.controller;

import com.amr.project.service.impl.MainPageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
//@ActiveProfiles("dev")
public class MainPageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MainPageServiceImpl mainPageService;

    @Test
    public void shouldCreateMock() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    void shouldShowMainPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("mainPageDto", mainPageService.getMainPageDto()));
    }

    @Test
    void getSalesHistory() throws Exception {
        mockMvc.perform(get("/sales"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType("text/html"))
                .andExpect(forwardedUrl("/salesHistory"));
    }
}
