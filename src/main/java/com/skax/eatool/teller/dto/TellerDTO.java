package com.skax.eatool.teller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 텔러 데이터 전송 객체
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TellerDTO {

    private Long id;
    private String tellerId;
    private String tellerName;
    private String branchCode;
    private String bankCode;
    private String status;
    private String registerDate;
    private String registerTime;
    private String registerBy;
    private String lastUpdateDate;
    private String lastUpdateTime;
    private String lastUpdateUserId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
