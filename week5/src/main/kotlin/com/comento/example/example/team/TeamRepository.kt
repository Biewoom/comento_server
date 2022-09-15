package com.comento.example.example.team

import org.springframework.data.repository.CrudRepository

interface TeamRepository: CrudRepository<Team, Long> {
}