package com.monolit.gamesjar.backend.communicator.service;

import com.monolit.gamesjar.backend.communicator.domain.History;
import com.monolit.gamesjar.backend.communicator.repository.CommunicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DialogService {

    @Autowired
    private CommunicatorRepository communicatorRepository;

    public void sentMessage(Long historyId, String infoToSent) throws Exception {
        /*RestTemplate rest = new RestTemplate();
        rest.exchange(
                String.format("http://localhost:8083/v1/gamesjar/history/%s?text=%s", historyId, infoToSent),
                HttpMethod.PUT,
                HttpEntity.EMPTY,
                String.class);*/
        History message = communicatorRepository.findById(historyId).orElseThrow(Exception::new);
        message.setHistory(message.getHistory() + infoToSent + System.lineSeparator());
        communicatorRepository.save(message);
    }

    public void clearHistory(Long historyId) throws Exception {
        History message = communicatorRepository.findById(historyId).orElseThrow(Exception::new);
        message.setHistory("");
        communicatorRepository.save(message);
    }
}