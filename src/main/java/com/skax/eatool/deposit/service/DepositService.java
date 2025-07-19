package com.skax.eatool.deposit.service;

import com.skax.eatool.deposit.dto.DepositDTO;
import com.skax.eatool.deposit.dto.DepositStatistics;
import com.skax.eatool.deposit.entity.Deposit;
import com.skax.eatool.deposit.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 예금 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DepositService {

    private final DepositRepository depositRepository;

    /**
     * 모든 예금 조회
     */
    public List<DepositDTO> getAllDeposits() {
        log.info("[DepositService] getAllDeposits START");

        List<DepositDTO> result = depositRepository.findAll()
                .stream()
                .filter(deposit -> !deposit.getIsDeleted())
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] getAllDeposits END - count: {}", result.size());
        return result;
    }

    /**
     * ID로 예금 조회
     */
    public Optional<DepositDTO> getDepositById(Long depositId) {
        log.info("[DepositService] getDepositById START - depositId: {}", depositId);

        Optional<DepositDTO> result = depositRepository.findById(depositId)
                .filter(deposit -> !deposit.getIsDeleted())
                .map(this::convertToDTO);

        log.info("[DepositService] getDepositById END - found: {}", result.isPresent());
        return result;
    }

    /**
     * 계좌번호로 예금 조회
     */
    public Optional<DepositDTO> getDepositByAccountNumber(String accountNumber) {
        log.info("[DepositService] getDepositByAccountNumber START - accountNumber: {}", accountNumber);

        Optional<DepositDTO> result = depositRepository.findByAccountNumber(accountNumber)
                .filter(deposit -> !deposit.getIsDeleted())
                .map(this::convertToDTO);

        log.info("[DepositService] getDepositByAccountNumber END - found: {}", result.isPresent());
        return result;
    }

    /**
     * 디버깅용: 계좌번호로 예금 조회 (삭제 여부 무시)
     */
    public List<DepositDTO> getDepositByAccountNumberDebug(String accountNumber) {
        log.info("[DepositService] getDepositByAccountNumberDebug START - accountNumber: {}", accountNumber);

        List<DepositDTO> result = depositRepository.findByAccountNumberDebug(accountNumber)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] getDepositByAccountNumberDebug END - count: {}", result.size());
        return result;
    }

    /**
     * 디버깅용: 모든 예금 조회 (삭제 여부 무시)
     */
    public List<DepositDTO> getAllDepositsDebug() {
        log.info("[DepositService] getAllDepositsDebug START");

        List<DepositDTO> result = depositRepository.findAllDebug()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] getAllDepositsDebug END - count: {}", result.size());
        return result;
    }

    /**
     * CIF 번호로 예금 조회
     */
    public List<DepositDTO> getDepositsByCifNo(String cifNo) {
        log.info("[DepositService] getDepositsByCifNo START - cifNo: {}", cifNo);

        List<DepositDTO> result = depositRepository.findByCifNo(cifNo)
                .stream()
                .filter(deposit -> !deposit.getIsDeleted())
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] getDepositsByCifNo END - count: {}", result.size());
        return result;
    }

    /**
     * 은행코드와 지점코드로 예금 조회
     */
    public List<DepositDTO> getDepositsByBankAndBranch(String bankCode, String branchCode) {
        log.info("[DepositService] getDepositsByBankAndBranch START - bankCode: {}, branchCode: {}", bankCode,
                branchCode);

        List<DepositDTO> result = depositRepository.findByBankCodeAndBranchCode(bankCode, branchCode)
                .stream()
                .filter(deposit -> !deposit.getIsDeleted())
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] getDepositsByBankAndBranch END - count: {}", result.size());
        return result;
    }

    /**
     * 상태로 예금 조회
     */
    public List<DepositDTO> getDepositsByStatus(String status) {
        log.info("[DepositService] getDepositsByStatus START - status: {}", status);

        List<DepositDTO> result = depositRepository.findByStatus(status)
                .stream()
                .filter(deposit -> !deposit.getIsDeleted())
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] getDepositsByStatus END - count: {}", result.size());
        return result;
    }

    /**
     * 활성 예금 조회
     */
    public List<DepositDTO> getActiveDeposits() {
        log.info("[DepositService] getActiveDeposits START");

        List<DepositDTO> result = depositRepository.findByStatus("A")
                .stream()
                .filter(deposit -> !deposit.getIsDeleted())
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] getActiveDeposits END - count: {}", result.size());
        return result;
    }

    /**
     * 검색조건으로 예금 조회
     */
    public List<DepositDTO> searchDeposits(String accountNumber, String cifNo, String cifName, String depositType,
            String status) {
        log.info(
                "[DepositService] searchDeposits START - accountNumber: {}, cifNo: {}, cifName: {}, depositType: {}, status: {}",
                accountNumber, cifNo, cifName, depositType, status);

        List<DepositDTO> result = depositRepository
                .findBySearchCriteria(accountNumber, cifNo, cifName, depositType, status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] searchDeposits END - count: {}", result.size());
        return result;
    }

    /**
     * 통계 정보 조회
     */
    public DepositStatistics getDepositStatistics() {
        log.info("[DepositService] getDepositStatistics START");

        Long totalDeposits = depositRepository.countActiveDeposits();
        Long activeDeposits = depositRepository.countActiveStatusDeposits();
        BigDecimal totalAmount = depositRepository.getTotalAmount();
        BigDecimal totalBalance = depositRepository.getTotalBalance();

        if (totalAmount == null)
            totalAmount = BigDecimal.ZERO;
        if (totalBalance == null)
            totalBalance = BigDecimal.ZERO;

        DepositStatistics statistics = DepositStatistics.builder()
                .totalDeposits(totalDeposits)
                .activeDeposits(activeDeposits)
                .totalAmount(totalAmount)
                .totalBalance(totalBalance)
                .build();

        log.info("[DepositService] getDepositStatistics END - total: {}, active: {}", totalDeposits, activeDeposits);
        return statistics;
    }

    /**
     * 최근 등록된 예금 조회
     */
    public List<DepositDTO> getRecentDeposits() {
        log.info("[DepositService] getRecentDeposits START");

        List<DepositDTO> result = depositRepository.findRecentDeposits()
                .stream()
                .filter(deposit -> !deposit.getIsDeleted())
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] getRecentDeposits END - count: {}", result.size());
        return result;
    }

    /**
     * 예금 생성
     */
    @Transactional
    public DepositDTO createDeposit(DepositDTO depositDTO) {
        log.info("[DepositService] createDeposit START - accountNumber: {}", depositDTO.getAccountNumber());

        Deposit deposit = convertToEntity(depositDTO);
        deposit.setCreatedDate(LocalDateTime.now());
        deposit.setModifiedDate(LocalDateTime.now());
        deposit.setIsDeleted(false);

        Deposit savedDeposit = depositRepository.save(deposit);
        DepositDTO result = convertToDTO(savedDeposit);

        log.info("[DepositService] createDeposit END - depositId: {}", result.getDepositId());
        return result;
    }

    /**
     * 예금 수정
     */
    @Transactional
    public DepositDTO updateDeposit(Long depositId, DepositDTO depositDTO) {
        log.info("[DepositService] updateDeposit START - depositId: {}", depositId);

        Optional<Deposit> existingDeposit = depositRepository.findById(depositId);
        if (existingDeposit.isEmpty()) {
            log.error("[DepositService] updateDeposit ERROR - Deposit not found: {}", depositId);
            throw new RuntimeException("Deposit not found: " + depositId);
        }

        Deposit deposit = existingDeposit.get();
        if (deposit.getIsDeleted()) {
            log.error("[DepositService] updateDeposit ERROR - Deposit is deleted: {}", depositId);
            throw new RuntimeException("Deposit is deleted: " + depositId);
        }

        // Update fields
        deposit.setCifName(depositDTO.getCifName());
        deposit.setDepositType(depositDTO.getDepositType());
        deposit.setInterestRate(depositDTO.getInterestRate());
        deposit.setMaturityDate(depositDTO.getMaturityDate());
        deposit.setStatus(depositDTO.getStatus());
        deposit.setModifiedDate(LocalDateTime.now());

        Deposit savedDeposit = depositRepository.save(deposit);
        DepositDTO result = convertToDTO(savedDeposit);

        log.info("[DepositService] updateDeposit END - depositId: {}", result.getDepositId());
        return result;
    }

    /**
     * 예금 삭제 (논리적 삭제)
     */
    @Transactional
    public void deleteDeposit(Long depositId) {
        log.info("[DepositService] deleteDeposit START - depositId: {}", depositId);

        Optional<Deposit> existingDeposit = depositRepository.findById(depositId);
        if (existingDeposit.isEmpty()) {
            log.error("[DepositService] deleteDeposit ERROR - Deposit not found: {}", depositId);
            throw new RuntimeException("Deposit not found: " + depositId);
        }

        Deposit deposit = existingDeposit.get();
        deposit.setIsDeleted(true);
        deposit.setModifiedDate(LocalDateTime.now());

        depositRepository.save(deposit);

        log.info("[DepositService] deleteDeposit END - depositId: {}", depositId);
    }

    /**
     * 예금 상태 변경
     */
    @Transactional
    public DepositDTO updateDepositStatus(Long depositId, String status) {
        log.info("[DepositService] updateDepositStatus START - depositId: {}, status: {}", depositId, status);

        Optional<Deposit> existingDeposit = depositRepository.findById(depositId);
        if (existingDeposit.isEmpty()) {
            log.error("[DepositService] updateDepositStatus ERROR - Deposit not found: {}", depositId);
            throw new RuntimeException("Deposit not found: " + depositId);
        }

        Deposit deposit = existingDeposit.get();
        if (deposit.getIsDeleted()) {
            log.error("[DepositService] updateDepositStatus ERROR - Deposit is deleted: {}", depositId);
            throw new RuntimeException("Deposit is deleted: " + depositId);
        }

        deposit.setStatus(status);
        deposit.setModifiedDate(LocalDateTime.now());

        Deposit savedDeposit = depositRepository.save(deposit);
        DepositDTO result = convertToDTO(savedDeposit);

        log.info("[DepositService] updateDepositStatus END - depositId: {}, status: {}", result.getDepositId(), status);
        return result;
    }

    /**
     * 예금 입금
     */
    @Transactional
    public DepositDTO depositMoney(Long depositId, BigDecimal amount) {
        log.info("[DepositService] depositMoney START - depositId: {}, amount: {}", depositId, amount);

        Optional<Deposit> existingDeposit = depositRepository.findById(depositId);
        if (existingDeposit.isEmpty()) {
            log.error("[DepositService] depositMoney ERROR - Deposit not found: {}", depositId);
            throw new RuntimeException("Deposit not found: " + depositId);
        }

        Deposit deposit = existingDeposit.get();
        if (deposit.getIsDeleted()) {
            log.error("[DepositService] depositMoney ERROR - Deposit is deleted: {}", depositId);
            throw new RuntimeException("Deposit is deleted: " + depositId);
        }

        BigDecimal currentBalance = deposit.getBalance() != null ? deposit.getBalance() : BigDecimal.ZERO;
        deposit.setBalance(currentBalance.add(amount));
        deposit.setModifiedDate(LocalDateTime.now());

        Deposit savedDeposit = depositRepository.save(deposit);
        DepositDTO result = convertToDTO(savedDeposit);

        log.info("[DepositService] depositMoney END - depositId: {}, newBalance: {}", result.getDepositId(),
                result.getBalance());
        return result;
    }

    /**
     * 예금 출금
     */
    @Transactional
    public DepositDTO withdrawMoney(Long depositId, BigDecimal amount) {
        log.info("[DepositService] withdrawMoney START - depositId: {}, amount: {}", depositId, amount);

        Optional<Deposit> existingDeposit = depositRepository.findById(depositId);
        if (existingDeposit.isEmpty()) {
            log.error("[DepositService] withdrawMoney ERROR - Deposit not found: {}", depositId);
            throw new RuntimeException("Deposit not found: " + depositId);
        }

        Deposit deposit = existingDeposit.get();
        if (deposit.getIsDeleted()) {
            log.error("[DepositService] withdrawMoney ERROR - Deposit is deleted: {}", depositId);
            throw new RuntimeException("Deposit is deleted: " + depositId);
        }

        BigDecimal currentBalance = deposit.getBalance() != null ? deposit.getBalance() : BigDecimal.ZERO;
        if (currentBalance.compareTo(amount) < 0) {
            log.error("[DepositService] withdrawMoney ERROR - Insufficient balance: {}, requested: {}", currentBalance,
                    amount);
            throw new RuntimeException("Insufficient balance");
        }

        deposit.setBalance(currentBalance.subtract(amount));
        deposit.setModifiedDate(LocalDateTime.now());

        Deposit savedDeposit = depositRepository.save(deposit);
        DepositDTO result = convertToDTO(savedDeposit);

        log.info("[DepositService] withdrawMoney END - depositId: {}, newBalance: {}", result.getDepositId(),
                result.getBalance());
        return result;
    }

    /**
     * Entity를 DTO로 변환
     */
    private DepositDTO convertToDTO(Deposit deposit) {
        return DepositDTO.builder()
                .depositId(deposit.getDepositId())
                .accountNumber(deposit.getAccountNumber())
                .cifNo(deposit.getCifNo())
                .cifName(deposit.getCifName())
                .bankCode(deposit.getBankCode())
                .branchCode(deposit.getBranchCode())
                .depositType(deposit.getDepositType())
                .amount(deposit.getAmount())
                .balance(deposit.getBalance())
                .interestRate(deposit.getInterestRate())
                .maturityDate(deposit.getMaturityDate())
                .status(deposit.getStatus())
                .createdDate(deposit.getCreatedDate())
                .modifiedDate(deposit.getModifiedDate())
                .createdBy(deposit.getCreatedBy())
                .modifiedBy(deposit.getModifiedBy())
                .isDeleted(deposit.getIsDeleted())
                .build();
    }

    /**
     * DTO를 Entity로 변환
     */
    private Deposit convertToEntity(DepositDTO depositDTO) {
        Deposit deposit = new Deposit();
        deposit.setDepositId(depositDTO.getDepositId());
        deposit.setAccountNumber(depositDTO.getAccountNumber());
        deposit.setCifNo(depositDTO.getCifNo());
        deposit.setCifName(depositDTO.getCifName());
        deposit.setBankCode(depositDTO.getBankCode());
        deposit.setBranchCode(depositDTO.getBranchCode());
        deposit.setDepositType(depositDTO.getDepositType());
        deposit.setAmount(depositDTO.getAmount());
        deposit.setBalance(depositDTO.getBalance());
        deposit.setInterestRate(depositDTO.getInterestRate());
        deposit.setMaturityDate(depositDTO.getMaturityDate());
        deposit.setStatus(depositDTO.getStatus());
        deposit.setCreatedDate(depositDTO.getCreatedDate());
        deposit.setModifiedDate(depositDTO.getModifiedDate());
        deposit.setCreatedBy(depositDTO.getCreatedBy());
        deposit.setModifiedBy(depositDTO.getModifiedBy());
        deposit.setIsDeleted(depositDTO.getIsDeleted());
        return deposit;
    }
}
