package com.comento.example.service

import com.comento.example.domain.common.vo.Expr.Num
import com.comento.example.domain.common.vo.Expr.Divide
import com.comento.example.domain.common.vo.Expr.Minus
import com.comento.example.domain.common.vo.Expr.Pow
import com.comento.example.domain.common.vo.Expr.Sum
import com.comento.example.domain.common.vo.Expr.Time
import com.comento.example.domain.common.vo.Expr
import com.comento.example.logger
import com.comento.example.toNumber
import com.comento.example.toRound
import org.springframework.stereotype.Service
import java.lang.Integer.max
import kotlin.random.Random

private fun String.getDecimalPoint() = this.split(".").getOrNull(1)?.length ?: 0

private inline fun <T> List<T>.lastIndexOrNull(predicate: (T) -> Boolean): Int? = run {
    val res = this.indexOfLast(predicate)
    if (res == -1) null
    else res
}


@Service
class CalculatorService {
    fun getRandomNum(range: String): Number {

        if (!range.contains("~")) throw IllegalArgumentException("request should contain `~`")

        val numbers = range.filter { !it.isWhitespace() }.split("~")

        if ( numbers.size != 2 ) throw IllegalArgumentException("request should contain `2 number values`")

        val (start, end) = numbers
        logger.info("start: $start , end: $end")

        val startNum = start.toNumber()
        val endNum = end.toNumber()

        val startNumDecimalPoint = start.getDecimalPoint()
        val endNumDecimalPoint = end.getDecimalPoint()
        logger.info("startNum: $startNum , end: $endNum")
        logger.info("startNumCounting: $startNumDecimalPoint , endNumCounting: $endNumDecimalPoint")

        return when {
            startNum is Long && endNum is Long -> Random.nextLong(startNum, endNum)
            else -> Random.nextDouble(startNum.toDouble(), endNum.toDouble())
                .toRound(max(startNumDecimalPoint, endNumDecimalPoint))
        }
    }

    fun calculate(expr: String, roundNum: Int): Double {
        val ll = makeStringList(expr)
        val expr = parseExpression(ll)
        return expr.evalFun().toRound(roundNum)
    }

    private fun makeStringList(str: String): List<String> {
        if (str.isEmpty()) return emptyList()
        val res = mutableListOf<String>()

        val strWithoutSpace = str.filter { !it.isWhitespace() }

        var num = ""
        strWithoutSpace.forEach {
            if (it in listOf('+', '-', '*', '/', '^')) {
                res.add(num)
                res.add(it.toString())
                num = ""
            } else {
                if (!it.isDigit()) throw IllegalArgumentException("`$it` should be digit ")
                num += it
            }
        }
        res.add(num)
        return res.toList()
    }

    private fun parseExpression(ll: List<String>): Expr {
        if (ll.size == 1) return Num(ll[0].toDouble())

        ll.lastIndexOrNull { it == "+" || it == "-" }?.let {
            when (ll[it]) {
                "+" -> return Sum(
                    parseExpression(ll.subList(0, it)),
                    parseExpression(ll.subList(it + 1, ll.size))
                )
                else -> return Minus(
                    parseExpression(ll.subList(0, it)),
                    parseExpression(ll.subList(it + 1, ll.size))
                )
            }
        }
        ll.lastIndexOrNull { it == "*" || it == "/" }?.let {
            when (ll[it]) {
                "*" -> return Time(
                    parseExpression(ll.subList(0, it)),
                    parseExpression(ll.subList(it + 1, ll.size))
                )
                else -> return Divide(
                    parseExpression(ll.subList(0, it)),
                    parseExpression(ll.subList(it + 1, ll.size))
                )
            }
        }
        ll.lastIndexOrNull { it == "^" }?.let {
            return Pow(
                parseExpression(ll.subList(0, it)),
                parseExpression(ll.subList(it + 1, ll.size))
            )
        }
        throw IllegalArgumentException("$ll cannot reach this line")
    }

}