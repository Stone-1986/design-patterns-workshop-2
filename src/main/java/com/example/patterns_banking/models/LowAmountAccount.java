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
public class LowAmountAccount extends Account {
    private static final BigDecimal DEPOSIT_FEE_RATE      = new BigDecimal("0.01");
    private static final BigDecimal FREE_TRANSACTION_THRESHOLD = new BigDecimal("100.00");

    @Override
    public BigDecimal calculateDepositFee(BigDecimal amount) {
        return Optional.ofNullable(amount)
                .filter(a -> a.compareTo(FREE_TRANSACTION_THRESHOLD) > 0)
                .map(a -> a.multiply(DEPOSIT_FEE_RATE))
                .orElse(BigDecimal.ZERO);
    }
}

//    Crear el factory para este tipo de cuentas
//    Crear un command para consultar todas las cuentas