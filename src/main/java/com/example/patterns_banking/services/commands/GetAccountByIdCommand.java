package com.example.patterns_banking.services.commands;

import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.repositories.IAccountRepository;

public class GetAccountByIdCommand implements ICommand<Account> {
    private final IAccountRepository accountRepository;
    private final Long accountId;

    public GetAccountByIdCommand(IAccountRepository accountRepository, Long accountId) {
        this.accountRepository = accountRepository;
        this.accountId = accountId;
    }

    @Override
    public Account execute() {
        return accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException("No se encontró la cuenta con id " + accountId)
                );
    }
}
