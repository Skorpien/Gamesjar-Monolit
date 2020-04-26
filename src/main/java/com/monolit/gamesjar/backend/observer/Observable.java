package com.monolit.gamesjar.backend.observer;

public interface Observable {
    void registerObserver(Observer observer);
    void notifyObservers() throws Exception;
    void removeObserver(Observer observer);
}
