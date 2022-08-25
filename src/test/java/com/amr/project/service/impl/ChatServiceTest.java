package com.amr.project.service.impl;

import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ChatService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatServiceTest {

    @Autowired
    private ChatService service;
    @MockBean
    EntityManager entityManager;

    @BeforeEach
    void createChat (EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Chat chat = new Chat();
        chat.setRecipient(entityManager.find(User.class, 2L));
        chat.setSender(entityManager.find(User.class, 1L));
        entityManager.persist(chat);

        entityManager.getTransaction().commit();
    }


    @Test
    public void existChatByUsersTest(){
        boolean exist1 = service.existChatByUsers("user", "moderator");
        boolean exist2 = service.existChatByUsers("moderator", "user");
        boolean exist3 = service.existChatByUsers("user", "user");
        boolean exist4 = service.existChatByUsers("admin", "admin");
        assertTrue(exist1);
        assertTrue(exist2);
        assertFalse(exist3);
        assertFalse(exist4);
    }

    @Test
    public void getChatByUsersTest(){
        assertThat(service.getChatByUsers("user", "moderator").getId()).isEqualTo(1L);
    }
    @Test
    public void getChatSetByUserNameTest(){
        assertThat(service.getChatSetByUserName("user").size()).isOne();
        assertThat(service.getChatSetByUserName("admin").size()).isZero();
    }
}
