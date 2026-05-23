package org.migel.trsis.samples.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Компьютерная игра")
public class GameDTO {
    @Schema(description = "Идентификатор игры", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Название игры", example = "Cyberpunk 2077")
    private String title;

    @Schema(description = "Студия разработчик", example = "CD Projekt RED")
    private String developer;

    @Schema(description = "Цена в долларах", example = "59.99")
    private Double price;

    @Schema(description = "Год выпуска", example = "2020")
    private Integer releaseYear;
}