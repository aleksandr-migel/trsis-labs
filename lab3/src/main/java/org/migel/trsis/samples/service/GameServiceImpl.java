package org.migel.trsis.samples.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.migel.trsis.samples.db.dao.GameRepository;
import org.migel.trsis.samples.db.model.GamePE;
import org.migel.trsis.samples.rest.model.GameDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ObjectMapper objectMapper;

    @Override
    public List<GameDTO> listAll() {
        return gameRepository.findAll().stream()
                .map(gamePE -> objectMapper.convertValue(gamePE, GameDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GameDTO findById(Long id) {
        return gameRepository.findById(id)
                .map(gamePE -> objectMapper.convertValue(gamePE, GameDTO.class))
                .orElse(null);
    }

    @Override
    public GameDTO add(GameDTO gameDTO) {
        GamePE gamePE = objectMapper.convertValue(gameDTO, GamePE.class);
        gamePE.setId(null);
        return objectMapper.convertValue(gameRepository.save(gamePE), GameDTO.class);
    }

    @Override
    public GameDTO update(Long id, GameDTO gameDTO) {
        if (!gameRepository.existsById(id)) {
            return null;
        }
        GamePE gamePE = objectMapper.convertValue(gameDTO, GamePE.class);
        gamePE.setId(id);
        return objectMapper.convertValue(gameRepository.save(gamePE), GameDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
        }
    }
}