package com.comento.example.domain.member

import org.springframework.data.repository.CrudRepository

interface MemberRepository: CrudRepository<Member, Long> {
}