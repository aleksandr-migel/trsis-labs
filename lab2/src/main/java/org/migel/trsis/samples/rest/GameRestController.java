package org.migel.trsis.samples.rest;

import org.migel.trsis.samples.rest.model.GameDTO;
import org.migel.trsis.samples.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/public/rest/games")
@RequiredArgsConstructor
public class GameRestController {
    private final GameService gameService;

    @Operation(summary = "Получить список всех игр", responses = {@ApiResponse(responseCode = "200")})
    @GetMapping
    public ResponseEntity<List<GameDTO>> browse() {
        return ResponseEntity.ok(gameService.listAll());
    }

    @Operation(
            summary = "Поиск игры по ID",
            responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")}
    )
    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> findById(@PathVariable("id") @Parameter(description = "ID игры") Long id) {
        GameDTO game = gameService.findById(id);
        return game != null ? ResponseEntity.ok(game) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Добавить новую игру", description = "Передайте JSON с данными игры в теле запроса",
            responses = {@ApiResponse(responseCode = "201", description = "Успешно создано")})
    @PostMapping
    public ResponseEntity<GameDTO> add(@RequestBody GameDTO gameDTO) {
        GameDTO created = gameService.add(gameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Обновить данные игры", description = "Передайте ID в URL и новый JSON в теле запроса",
            responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> update(
            @PathVariable("id") @Parameter(description = "ID игры") Long id,
            @RequestBody GameDTO gameDTO) {
        GameDTO updated = gameService.update(id, gameDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Удалить игру", responses = {@ApiResponse(responseCode = "200")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(description = "ID игры") Long id) {
        gameService.delete(id);
        return ResponseEntity.ok().build();
    }
}
