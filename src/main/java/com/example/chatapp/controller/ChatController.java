package com.example.chatapp.controller;

import com.example.chatapp.model.Message;
import com.example.chatapp.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return chatService.getAllMessages();
    }

    @PostMapping
    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {
        Message savedMessage = chatService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
}
