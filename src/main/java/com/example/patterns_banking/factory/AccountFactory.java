package com.example.patterns_banking.factory;

import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.Customer;

import java.math.BigDecimal;

public interface AccountFactory {
  Account createAccount(Customer customer, String accountNumber, BigDecimal balance);
}
