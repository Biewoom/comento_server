package com.comento.example.presentation

import io.swagger.v3.oas.annotations.media.Schema

data class Calculator(
    @Schema(title = "expression", example = "1 + 10 * 5")  val expression: String,
    @Schema(title = "round", description = "소숫점 반올림 자리", example = "1")  val round: Int
)

@Schema(title = "[Message] 메세지 DTO")
data class Message(
    @Schema(title = "메세지 ID", example = "id1") val id: String,
    @Schema(title = "메세지 text", example = "good") val text: String?
)
