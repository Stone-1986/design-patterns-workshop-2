package com.example.patterns_banking.mapper;

import com.example.patterns_banking.dtos.AccountDTO;
import com.example.patterns_banking.factory.AccountFactoryProvider;
import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.CheckingAccount;
import com.example.patterns_banking.models.LowAmountAccount;
import com.example.patterns_banking.models.SavingsAccount;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    public AccountDTO toDTO(Account account) {
        if (account == null) {
            return null;
        }
        AccountFactoryProvider.AccountType type;
        if (account instanceof CheckingAccount) {
            type = AccountFactoryProvider.AccountType.CHECKING;
        } else if (account instanceof SavingsAccount) {
            type = AccountFactoryProvider.AccountType.SAVINGS;
        } else if (account instanceof LowAmountAccount) {
            type = AccountFactoryProvider.AccountType.LOW_AMOUNT;
        } else {
            throw new IllegalArgumentException(
                    "Tipo de cuenta desconocido: " + account.getClass().getSimpleName()
            );
        }

        return new AccountDTO(
                account.getAccountNumber(),
                account.getBalance(),
                account.getCustomer() != null ? account.getCustomer().getId() : null,
                type
        );
    }

     public List<AccountDTO> toDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
