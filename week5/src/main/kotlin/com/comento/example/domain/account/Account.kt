package com.comento.example.domain.account

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

@Entity
@Table(name = "account")
@IdClass(Account.AccountId::class)
data class Account(
    @Id
    @Column(name = "account_number")
    val accountNumber: String,
    @Id
    @Column(name = "bank")
    val bank: Short
) {
    @Column(name = "balance")
    var balance: Int = 0

    inner class AccountId: Serializable {
        var accountNumber: String? = null
        var bank: Short? = null
    }
}