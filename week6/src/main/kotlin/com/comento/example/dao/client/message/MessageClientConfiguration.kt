package com.comento.example.dao.client.message

import com.comento.example.logger
import feign.Logger
import feign.Response
import feign.RetryableException
import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus

class MessageClientConfiguration: FeignClientConfiguration() {

    @Bean
    fun retryer() = Retryer.Default(
        1000L,
        1000L,
        5
    )

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun errorDecoder() = object: ErrorDecoder {

        override fun decode(methodKey: String?, response: Response?): Exception {
            logger.info { "Hello" }

            when (response?.status()){
//                400 -> return  Bad
//                404 -> return  NotFoundException(HttpStatus.valueOf(response.status()).toJson())
//                500 -> return InternalServerException(HttpStatus.valueOf(response.status()))
                HttpStatus.CONFLICT.value(), HttpStatus.SERVICE_UNAVAILABLE.value() -> return RetryableException(response.status(),response.reason(), response.request().httpMethod(), null, response.request())
            }
            return RuntimeException("This Status Cannot be good")
        }
    }
}