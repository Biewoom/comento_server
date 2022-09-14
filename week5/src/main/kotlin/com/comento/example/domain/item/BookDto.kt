package com.comento.example.domain.item

import java.io.Serializable

data class BookDto(val name: String? = null, val price: Int = 0, val author: String? = null, val isbn: String? = null) :
    Serializable
