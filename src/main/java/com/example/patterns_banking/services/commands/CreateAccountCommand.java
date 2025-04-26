package com.example.patterns_banking.services.commands;

import com.example.patterns_banking.dtos.AccountDTO;
import com.example.patterns_banking.factory.AccountFactory;
import com.example.patterns_banking.factory.AccountFactoryProvider;
import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.IAccountRepository;
import com.example.patterns_banking.repositories.ICustomerRepository;

import java.util.Optional;

public class CreateAccountCommand implements ICommand<Account> {
  private final IAccountRepository accountRepository;
  private final ICustomerRepository customerRepository;
  private final AccountFactoryProvider accountFactoryProvider;
  private final AccountDTO accountDTO;

  public CreateAccountCommand(IAccountRepository accountRepository, ICustomerRepository customerRepository, AccountFactoryProvider accountFactoryProvider, AccountDTO accountDTO) {
    this.accountRepository = accountRepository;
    this.customerRepository = customerRepository;
    this.accountFactoryProvider = accountFactoryProvider;
    this.accountDTO = accountDTO;
  }

  @Override
  public Account execute() {
    Optional<Customer> customerOptional = customerRepository.findById(accountDTO.getCustomerId());

    if (customerOptional.isEmpty()) {
      throw new RuntimeException("Customer not found");
    }

    Customer customer = customerOptional.get();
    AccountFactory accountFactory = accountFactoryProvider.getFactory(accountDTO.getAccountType());
    Account account = accountFactory.createAccount(
            customer, accountDTO.getAccountNumber(),
            accountDTO.getBalance()
    );
    return accountRepository.save(account);
  }
}
