package com.monolit.gamesjar.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "gamename")
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "playingtime")
    private PlayingTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "categories")
    private Category categories;

    @ManyToOne
    @JoinColumn(name = "room_ID")
    private Room room;


    public Game(Long id, String name, PlayingTime time, Category categories) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.categories = categories;
    }

    public Game(String name, PlayingTime time, Category categories) {
        this.name = name;
        this.time = time;
        this.categories = categories;
    }

    public String getTimeToString() {
        return this.time.getTime();
    }
}
