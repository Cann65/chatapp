package com.example.chatapp;

import com.example.chatapp.model.Message;
import com.example.chatapp.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Import(ChatService.class)
class ChatServiceTest {
    @Autowired
    private ChatService service;

    @Test
    void storesAndReadsSyntheticMessage() {
        Message draft = new Message();
        draft.setSender("demo-user");
        draft.setContent("Synthetic message");

        assertThat(service.saveMessage(draft).getId()).isNotNull();
        assertThat(service.getAllMessages()).extracting(Message::getContent).containsExactly("Synthetic message");
    }
}
