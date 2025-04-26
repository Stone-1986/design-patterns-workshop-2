package com.example.patterns_banking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String accountNumber;

  @Column(precision = 19, scale = 2)
  private BigDecimal balance = BigDecimal.ZERO;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  @JsonBackReference
  private Customer customer;

  public abstract BigDecimal calculateDepositFee(BigDecimal amount);

  public void deposit(BigDecimal amount) {
    BigDecimal current = Optional.ofNullable(balance).orElse(BigDecimal.ZERO);
    BigDecimal fee     = calculateDepositFee(amount);
    BigDecimal net     = amount.subtract(fee);
    this.balance = current.add(net);
  }
}
