package com.comento.example.domain.team

import org.springframework.data.repository.CrudRepository

interface TeamRepository: CrudRepository<Team, Long> {
}