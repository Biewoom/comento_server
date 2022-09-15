package com.comento.example.domain.member

import com.comento.example.example.member.Member
import com.comento.example.example.member.MemberRepository
import com.comento.example.example.team.Team
import com.comento.example.example.team.TeamRepository
import com.comento.example.logger
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest(showSql = true)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class MemberRepositoryJpaTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var teamRepository: TeamRepository

    @DisplayName("Member 생성 테스트")
    @Test
    @Order(1)
    fun `test member create test`(){
        // given
        val samsungTeam = Team(null).apply { name = "삼성 라이온즈" }
        val member = Member(null).apply { name = "오승환"; team = samsungTeam }

        // when
        val res1 = teamRepository.save(samsungTeam)
        val res2 = memberRepository.save(member)
        val members = memberRepository.findAll()

        // then
        logger.info { "res: $res2" }
        logger.info { "members: $members "}
    }

    @Test
    @Order(2)
    fun `test After Create`(){
        val members = memberRepository.findAll()

        logger.info { "members: $members "}
    }
}