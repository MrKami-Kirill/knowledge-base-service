package ru.tecius.api.dto.document.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;

@Schema(description = "Данные о версии документа")
public record DocumentHistoryDataDto(
    @Schema(description = "Версия документа", example = "1", requiredMode = REQUIRED)
    Integer version,
    @Schema(description = "Название элемента меню/документа", example = "Страница",
        requiredMode = REQUIRED)
    String title,
    @Schema(description = "Дата публикации версии документа", example = "2025-12-25Т10:10:10+03:00",
        requiredMode = REQUIRED)
    OffsetDateTime date,
    @Schema(description = "Кто опубликовал версию документа", example = "Иванов Иван Иванович",
        requiredMode = REQUIRED)
    String lastModifier
) {

}
