package org.migel.trsis.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {
    private Long id;
    private String title;
    private String developer;
    private Double price;
    private Integer releaseYear;
}