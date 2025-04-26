package com.example.patterns_banking.factory;

import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.models.LowAmountAccount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LowAmountAccountFactory implements AccountFactory{
    @Override
    public Account createAccount(Customer customer, String accountNumber, BigDecimal balance) {
        LowAmountAccount account = new LowAmountAccount();
        account.setCustomer(customer);
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        return account;
    }
}
