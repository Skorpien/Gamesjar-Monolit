package com.monolit.gamesjar.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameDto {
    private Long id;
    private String name;
    private PlayingTime time;
    private Category categories;
}
