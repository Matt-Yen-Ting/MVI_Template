package com.example.domain.usecase.account

import com.example.domain.repository.account.AccountRepository
import javax.inject.Inject

class Logout @Inject constructor(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke() {
        accountRepository.logout()
    }
}