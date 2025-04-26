package com.example.patterns_banking.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class CheckingAccount extends Account{
  private static final BigDecimal MANAGEMENT_FEE      = new BigDecimal("10.0"); // Monthly management fee
  private static final BigDecimal TRANSFER_FEE_RATE   = new BigDecimal("0.023"); // 2.3% of the amount
  private static final BigDecimal DEPOSIT_FEE_RATE    = new BigDecimal("0.01"); // 1% of the amount
  private static final BigDecimal WITHDRAWAL_FEE_RATE = new BigDecimal("0.002"); // 0.2% of the amount

  @Override
  public BigDecimal calculateDepositFee(BigDecimal amount) {
    return Optional.ofNullable(amount)
            .map(a -> a.multiply(DEPOSIT_FEE_RATE))
            .orElse(BigDecimal.ZERO);
  }
}
