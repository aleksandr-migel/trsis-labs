package org.migel.trsis.lab1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class GameRepository {
    private static final List<Game> games = new ArrayList<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    static {
        games.add(new Game(idGenerator.getAndIncrement(), "The Witcher 3", "CD Projekt RED", 29.99, 2015));
        games.add(new Game(idGenerator.getAndIncrement(), "Cyberpunk 2077", "CD Projekt RED", 59.99, 2020));
    }

    public static List<Game> findAll() {
        return games;
    }

    public static void add(String title, String developer, Double price, Integer releaseYear) {
        games.add(new Game(idGenerator.getAndIncrement(), title, developer, price, releaseYear));
    }

    public static void delete(Long id) {
        games.removeIf(game -> game.getId().equals(id));
    }
}