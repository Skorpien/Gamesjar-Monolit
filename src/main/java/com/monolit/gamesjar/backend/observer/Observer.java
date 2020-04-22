package com.monolit.gamesjar.backend.observer;

import com.monolit.gamesjar.backend.communicator.domain.History;

public interface Observer {
    String update(History history);
}
