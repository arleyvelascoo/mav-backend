package com.example.mavbackend.service.interfac;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ISendEmailService {
    @Async
    void sendSimpleEmail(String asunto, String texto, String footer, List<String> destinatarios,
                         Map<String, String> mapInlineImages);
}
