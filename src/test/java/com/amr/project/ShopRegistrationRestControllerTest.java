package com.amr.project;

import com.amr.project.converter.ShopMapper;
import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
@WithUserDetails
class ShopRegistrationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShopDao shopDao;
    @MockBean
    private ShopService shopService;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldAddShopToModerate() throws Exception {
        ShopDto shopDto2 = shopMapper.toDto(shopDao.findById(1L).get());
        shopDto2.setId(null);
        shopDto2.getLogo().setId(null);
        shopDto2.setDiscounts(null);
        shopDto2.getLocation().setId(null);
        shopDto2.getAddressDetails().setId(null);
        MvcResult mvcResult = mockMvc.perform(post("/shop/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(shopDto2)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
