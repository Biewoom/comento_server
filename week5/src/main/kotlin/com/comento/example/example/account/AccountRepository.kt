package com.comento.example.example.account

import org.springframework.data.repository.CrudRepository

interface AccountRepository: CrudRepository<Account, Account.AccountId> {

}