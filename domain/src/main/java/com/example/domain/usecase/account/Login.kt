package com.example.domain.usecase.account

import com.example.domain.repository.account.AccountRepository
import javax.inject.Inject

class Login @Inject constructor(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(account: String) {
        accountRepository.login(account)
    }
}