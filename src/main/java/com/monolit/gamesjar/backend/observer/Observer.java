package com.monolit.gamesjar.backend.observer;

import com.monolit.gamesjar.backend.communicator.domain.History;

public interface Observer {
    void update(History history) throws Exception;
}
