package com.amr.project;

import com.amr.project.service.abstracts.ShopService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc()
@WithUserDetails
class ShopRegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShopService shopService;


    @Test
    void shouldShowShopRegistrationPageTest() throws Exception {
        mockMvc
                .perform(get("/shop/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("shopRegistrationPage"))
                .andExpect(model().attributeExists("authUser", "cities", "countries"))
                .andDo(print());
    }


}
