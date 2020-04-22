package com.monolit.gamesjar.backend.domain;

import com.monolit.gamesjar.backend.communicator.domain.History;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "rooms")
    private List<User> users = new ArrayList<>();

    @OneToMany(
            targetEntity = Game.class,
            mappedBy = "room",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Game> games = new ArrayList<>();


    public Room(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "History_id")
    private History history;
}
