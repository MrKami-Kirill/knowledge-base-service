package ru.tecius.api.dto.document.session.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для получения сессии документа")
public record SessionResponseDto(
    @Schema(description = "Редактируется/не редактируется документ другим пользователем",
        example = "true",
        requiredMode = REQUIRED)
    Boolean locked
) {

}
