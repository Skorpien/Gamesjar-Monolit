package com.monolit.gamesjar.backend.communicator.domain;

import com.monolit.gamesjar.backend.domain.Room;
import com.monolit.gamesjar.backend.observer.Observable;
import com.monolit.gamesjar.backend.observer.Observer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity(name = "history")
public class History implements Observable {

    @Transient
    private List<Observer> observers = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "history")
    private String history = "";

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "Room_id")
    private Room room = new Room();

    public History(Long id, String history) {
        this.id = id;
        this.history = history;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() throws Exception {
        for(Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setHistory(String history) throws Exception {
        this.history = history;
        notifyObservers();
    }
}
