package com.comento.example.presentation

import com.comento.example.example.item.Book
import com.comento.example.example.item.BookRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/items")
class ItemController(
    private val bookRepository: BookRepository
) {

    @PostMapping
    fun addItem(){
        val book = Book().apply {
            name = "test"
            price = 5_000
            author = "Kim"
        }
        bookRepository.save(book)

    }


}