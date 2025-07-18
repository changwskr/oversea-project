package com.skcc.oversea.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 예금 데이터 전송 객체
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositDTO {

    private Long depositId;
    private String accountNumber;
    private String bankCode;
    private String branchCode;
    private String cifNo;
    private String cifName;
    private String depositType;
    private BigDecimal amount;
    private BigDecimal balance;
    private String currency;
    private BigDecimal interestRate;
    private LocalDateTime maturityDate;
    private String openDate;
    private String registerDate;
    private String registerTime;
    private String registerBy;
    private String status;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long version;
    private String createdBy;
    private String modifiedBy;
    private Boolean isDeleted;
}