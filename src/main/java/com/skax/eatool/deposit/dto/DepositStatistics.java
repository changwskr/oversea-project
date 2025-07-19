package com.skax.eatool.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 예금 통계 정보 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositStatistics {

    private Long totalDeposits;
    private Long activeDeposits;
    private BigDecimal totalAmount;
    private BigDecimal totalBalance;
}
