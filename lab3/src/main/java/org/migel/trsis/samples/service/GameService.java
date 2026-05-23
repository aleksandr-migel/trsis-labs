package org.migel.trsis.samples.service;

import org.migel.trsis.samples.rest.model.GameDTO;
import java.util.List;

public interface GameService {
    List<GameDTO> listAll();
    GameDTO findById(Long id);
    GameDTO add(GameDTO gameDTO);
    GameDTO update(Long id, GameDTO gameDTO);
    void delete(Long id);
}