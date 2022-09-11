package com.comento.example.domain.common.vo

import kotlin.math.pow

sealed class Expr {
    data class Num(val value: Double): Expr(){ override fun evalFun(): Double = value }
    data class Sum(val left: Expr, val right: Expr): Expr() { override fun evalFun(): Double = left.evalFun() + right.evalFun() }
    data class Minus(val left: Expr, val right: Expr): Expr(){ override fun evalFun(): Double = left.evalFun() - right.evalFun() }
    data class Time(val left: Expr, val right: Expr): Expr(){ override fun evalFun(): Double = left.evalFun() * right.evalFun() }
    data class Divide(val left: Expr, val right: Expr): Expr(){ override fun evalFun(): Double = left.evalFun() / right.evalFun() }
    data class Pow(val left: Expr, val right: Expr): Expr(){ override fun evalFun(): Double = left.evalFun().pow(right.evalFun()) }
    abstract fun evalFun():Double

}