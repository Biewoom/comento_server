package com.comento.example.domain.item;

import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<Book, Long> {
}