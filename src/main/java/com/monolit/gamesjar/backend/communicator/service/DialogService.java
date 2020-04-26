package com.monolit.gamesjar.backend.communicator.service;

import com.monolit.gamesjar.backend.communicator.domain.History;
import com.monolit.gamesjar.backend.communicator.repository.CommunicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DialogService {

    @Autowired
    private CommunicatorRepository communicatorRepository;

    public History sentMessage(Long historyId, String infoToSent) throws Exception {
        /*RestTemplate rest = new RestTemplate();
        rest.exchange(
                String.format("http://localhost:8083/v1/gamesjar/history/%s?text=%s", historyId, infoToSent),
                HttpMethod.PUT,
                HttpEntity.EMPTY,
                String.class);*/
        History message = communicatorRepository.findById(historyId).orElseThrow(Exception::new);
        message.setHistory(message.getHistory() + infoToSent + System.lineSeparator());
        return communicatorRepository.save(message);
    }
}