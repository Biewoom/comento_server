package com.comento.example.domain.account

import org.springframework.data.repository.CrudRepository

interface AccountRepository: CrudRepository<Account, Account.AccountId> {

}