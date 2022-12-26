package com.comento.example.presentation.filter

import org.slf4j.MDC
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

@Component
@Order(1)
class MdcFilter: Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        MDC.clear() // 이전 MDC 삭제.
        MDC.put("requestId", UUID.randomUUID().toString())

        chain?.doFilter(request, response)
    }


}