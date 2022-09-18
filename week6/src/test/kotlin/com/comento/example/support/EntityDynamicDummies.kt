package com.comento.example.support

import com.comento.example.domain.common.enums.Gender
import com.comento.example.domain.person.Person
//import com.comento.example.presentation.dto.PersonDto

private inline fun <reified T> MutableCollection<T>.addNulls(size: Int = 1): List<T?> {
    val nullsList = arrayOfNulls<T?>(size)
    return this + listOf(*nullsList)
}

val randomName: String
    get() = ("허승기, 설지태, 이상우, 사공서준, 예인영, 남희아, 김병욱, 신해빈, 문병희, 심대철, 예효주, 하선빈, 탁은우, 조인욱, 예철민, 윤시환, 탁창현, 설경옥, 추혜성, 문남일" + "Agatha, Aileen, cupboard, inch, loyalty, imitate, flatten, past").split(", ").random()
val randomAge: Int?
    get() = (0..100).toMutableList().addNulls(10).random()
val randomHeight: Int
    get() = (130..200).random()
val randomWeight: Int
    get() = (60..120).random()
val randomGender: Gender
    get() = Gender.values().random()
val randomGenderWithNulls: Gender?
    get() = Gender.values().toMutableList().addNulls(1).random()
val randomCountry: String
    get() = listOf("USA", "KOREA", "CHINA", "JAPAN", "RUSSIA", "TAIWAN", "VIETNAM" , "IRELAND", "ENGLAND", "CANADA").random()


//val Person.Companion.DYNAMIC_DUMMY : Person
//    get() = Person(name = randomName, gender = randomGender, country = randomCountry)
//        .apply { age = randomAge; height = randomHeight; weight = randomWeight; updateId((1..1000).random()) }

//val PersonDto.Companion.DYNAMIC_DUMMY: PersonDto
//    get() = PersonDto(
//        personId = (0.. 30).toMutableList().addNulls(8).random(),
//        age = randomAge,
//        height = randomHeight,
//        weight = randomWeight,
//        name = randomName,
//        gender = randomGenderWithNulls,
//        isMarried = listOf(true, false, null).random(),
//        company = listOf("GOOGLE", "META", "KAKAO", "DAEWO", "SHELL", "NAVER", null, null).random(),
//        country = randomCountry
//    )