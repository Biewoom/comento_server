package com.comento.example.example.member

import org.springframework.data.repository.CrudRepository

interface MemberRepository: CrudRepository<Member, Long> {
}