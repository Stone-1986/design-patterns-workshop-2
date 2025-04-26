package com.example.patterns_banking.factory;

import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.models.SavingsAccount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SavingsAccountFactory  implements AccountFactory {
  @Override
  public Account createAccount(Customer customer, String accountNumber, BigDecimal balance) {
    SavingsAccount account = new SavingsAccount();
    account.setAccountNumber(accountNumber);
    account.setBalance(balance);
    account.setCustomer(customer);
    return account;
  }
}
