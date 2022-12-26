package com.comento.example.dao.client.example

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "[Message] 메세지 DTO")
data class MessageDto(
    @Schema(title = "메세지 ID", example = "id1") val id: String,
    @Schema(title = "메세지 text", example = "good") val text: String?
)

