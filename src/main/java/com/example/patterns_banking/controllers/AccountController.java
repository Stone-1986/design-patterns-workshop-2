package com.example.patterns_banking.controllers;

import com.example.patterns_banking.dtos.AccountDTO;
import com.example.patterns_banking.mapper.AccountMapper;
import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {
  private final AccountService accountService;
  private final AccountMapper accountMapper;

  public AccountController(AccountService accountService, AccountMapper accountMapper) {
    this.accountService = accountService;
      this.accountMapper = accountMapper;
  }

  @PostMapping
  public Account createAccount(@RequestBody AccountDTO account) {
    return accountService.createAccount(account);
  }

  @GetMapping("/{accountId}/deposit")
  public Account deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
    return accountService.deposit(accountId, amount);
  }

  @GetMapping
  public ResponseEntity<List<AccountDTO>> listAll() {
    var dtos = accountMapper.toDTOList(accountService.listAccounts());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
    var account = accountService.getAccountById(id);
    var responseDto = accountMapper.toDTO(account);
    return ResponseEntity.ok(responseDto);
  }
}
