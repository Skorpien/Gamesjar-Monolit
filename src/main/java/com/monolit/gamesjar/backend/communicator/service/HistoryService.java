package com.monolit.gamesjar.backend.communicator.service;

import com.monolit.gamesjar.backend.communicator.repository.CommunicatorRepository;
import com.monolit.gamesjar.backend.communicator.domain.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private CommunicatorRepository communicatorRepository;

    public History getHistory(final Long historyId) throws Exception {
        return communicatorRepository.findById(historyId).orElseThrow(Exception::new);
    }

    public History addHistory(String text, final Long historyId) throws Exception {
        History message = communicatorRepository.findById(historyId).orElseThrow(Exception::new);
        message.setHistory(message.getHistory() + text + System.lineSeparator());
        return communicatorRepository.save(message);
    }

    public History createHistory(final History history) {
        return communicatorRepository.save(history);
    }

    public List<History> getAllHistories() {
        return communicatorRepository.findAll();
    }

    public void deleteHistory(final Long historyId) {
        communicatorRepository.deleteById(historyId);
    }

    public void clearHistory(final Long historyId) throws Exception {
        History clear = communicatorRepository.findById(historyId).orElseThrow(Exception::new);
        clear.setHistory("");
    }
}
