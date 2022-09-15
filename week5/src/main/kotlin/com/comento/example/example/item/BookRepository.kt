package com.comento.example.example.item;

import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<Book, Long> {
}