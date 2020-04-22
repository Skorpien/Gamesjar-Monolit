package com.monolit.gamesjar.backend.communicator;

import com.monolit.gamesjar.backend.communicator.service.DialogService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommunicatorGui {
    @Autowired
    private DialogService dialogService;

    public void sentMessage(Long id, String userName, String message) throws Exception {
            dialogService.sentMessage(id,userName + ": " + message);
    }

}
