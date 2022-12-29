package com.example.week4.domain.common

@JvmInline
value class YMD(private val str: String){

    init {
        val re = Regex("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
        if (!re.matches(str)) throw IllegalArgumentException("Incorrect Format")
    }

    override fun toString() = str
}

