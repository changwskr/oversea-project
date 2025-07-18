package com.skcc.oversea.deposit.service;

import com.skcc.oversea.deposit.dto.DepositDTO;
import com.skcc.oversea.deposit.dto.DepositStatistics;
import com.skcc.oversea.deposit.entity.Deposit;
import com.skcc.oversea.deposit.repository.DepositRepository;
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
     * 디버깅용: 계좌번호로 예금 조회 (삭제 여부 무관)
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
     * 디버깅용: 모든 예금 조회 (삭제 여부 무관)
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
     * 검색 조건으로 예금 조회
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
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[DepositService] getRecentDeposits END - count: {}", result.size());
        return result;
    }

    /**
     * 예금 등록
     */
    @Transactional
    public DepositDTO createDeposit(DepositDTO depositDTO) {
        log.info("[DepositService] createDeposit START - accountNumber: {}", depositDTO.getAccountNumber());

        // 계좌번호 중복 확인
        if (depositRepository.existsByAccountNumber(depositDTO.getAccountNumber())) {
            throw new RuntimeException("이미 존재하는 계좌번호입니다: " + depositDTO.getAccountNumber());
        }

        Deposit deposit = convertToEntity(depositDTO);
        deposit.setCreatedDate(LocalDateTime.now());
        deposit.setModifiedDate(LocalDateTime.now());
        deposit.setIsDeleted(false);
        deposit.setBalance(deposit.getAmount()); // 초기 잔액은 입금액과 동일

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

        Optional<Deposit> optionalDeposit = depositRepository.findById(depositId);
        if (optionalDeposit.isPresent()) {
            Deposit existingDeposit = optionalDeposit.get();

            if (existingDeposit.getIsDeleted()) {
                throw new RuntimeException("삭제된 예금입니다: " + depositId);
            }

            // 업데이트할 필드들 설정
            existingDeposit.setCifName(depositDTO.getCifName());
            existingDeposit.setDepositType(depositDTO.getDepositType());
            existingDeposit.setAmount(depositDTO.getAmount());
            existingDeposit.setBalance(depositDTO.getBalance());
            existingDeposit.setCurrency(depositDTO.getCurrency());
            existingDeposit.setInterestRate(depositDTO.getInterestRate());
            existingDeposit.setMaturityDate(depositDTO.getMaturityDate());
            existingDeposit.setStatus(depositDTO.getStatus());
            existingDeposit.setDescription(depositDTO.getDescription());
            existingDeposit.setModifiedDate(LocalDateTime.now());
            existingDeposit.setModifiedBy(depositDTO.getModifiedBy());

            Deposit updatedDeposit = depositRepository.save(existingDeposit);
            DepositDTO result = convertToDTO(updatedDeposit);

            log.info("[DepositService] updateDeposit END - depositId: {}", result.getDepositId());
            return result;
        }

        log.error("[DepositService] updateDeposit END - Deposit not found with id: {}", depositId);
        throw new RuntimeException("예금을 찾을 수 없습니다: " + depositId);
    }

    /**
     * 예금 삭제 (논리적 삭제)
     */
    @Transactional
    public void deleteDeposit(Long depositId) {
        log.info("[DepositService] deleteDeposit START - depositId: {}", depositId);

        Optional<Deposit> optionalDeposit = depositRepository.findById(depositId);
        if (optionalDeposit.isPresent()) {
            Deposit deposit = optionalDeposit.get();
            deposit.setIsDeleted(true);
            deposit.setModifiedDate(LocalDateTime.now());
            depositRepository.save(deposit);
            log.info("[DepositService] deleteDeposit END - depositId: {}", depositId);
        } else {
            log.error("[DepositService] deleteDeposit END - Deposit not found with id: {}", depositId);
            throw new RuntimeException("예금을 찾을 수 없습니다: " + depositId);
        }
    }

    /**
     * 예금 상태 변경
     */
    @Transactional
    public DepositDTO updateDepositStatus(Long depositId, String status) {
        log.info("[DepositService] updateDepositStatus START - depositId: {}, status: {}", depositId, status);

        Optional<Deposit> optionalDeposit = depositRepository.findById(depositId);
        if (optionalDeposit.isPresent()) {
            Deposit deposit = optionalDeposit.get();
            if (deposit.getIsDeleted()) {
                throw new RuntimeException("삭제된 예금입니다: " + depositId);
            }

            deposit.setStatus(status);
            deposit.setModifiedDate(LocalDateTime.now());

            Deposit updatedDeposit = depositRepository.save(deposit);
            DepositDTO result = convertToDTO(updatedDeposit);

            log.info("[DepositService] updateDepositStatus END - depositId: {}", result.getDepositId());
            return result;
        }

        log.error("[DepositService] updateDepositStatus END - Deposit not found with id: {}", depositId);
        throw new RuntimeException("예금을 찾을 수 없습니다: " + depositId);
    }

    /**
     * 예금 입금
     */
    @Transactional
    public DepositDTO depositMoney(Long depositId, BigDecimal amount) {
        log.info("[DepositService] depositMoney START - depositId: {}, amount: {}", depositId, amount);

        Optional<Deposit> optionalDeposit = depositRepository.findById(depositId);
        if (optionalDeposit.isPresent()) {
            Deposit deposit = optionalDeposit.get();
            if (deposit.getIsDeleted()) {
                throw new RuntimeException("삭제된 예금입니다: " + depositId);
            }

            BigDecimal newBalance = deposit.getBalance().add(amount);
            deposit.setBalance(newBalance);
            deposit.setModifiedDate(LocalDateTime.now());

            Deposit updatedDeposit = depositRepository.save(deposit);
            DepositDTO result = convertToDTO(updatedDeposit);

            log.info("[DepositService] depositMoney END - depositId: {}, newBalance: {}", result.getDepositId(),
                    newBalance);
            return result;
        }

        log.error("[DepositService] depositMoney END - Deposit not found with id: {}", depositId);
        throw new RuntimeException("예금을 찾을 수 없습니다: " + depositId);
    }

    /**
     * 예금 출금
     */
    @Transactional
    public DepositDTO withdrawMoney(Long depositId, BigDecimal amount) {
        log.info("[DepositService] withdrawMoney START - depositId: {}, amount: {}", depositId, amount);

        Optional<Deposit> optionalDeposit = depositRepository.findById(depositId);
        if (optionalDeposit.isPresent()) {
            Deposit deposit = optionalDeposit.get();
            if (deposit.getIsDeleted()) {
                throw new RuntimeException("삭제된 예금입니다: " + depositId);
            }

            if (deposit.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("잔액이 부족합니다. 현재 잔액: " + deposit.getBalance());
            }

            BigDecimal newBalance = deposit.getBalance().subtract(amount);
            deposit.setBalance(newBalance);
            deposit.setModifiedDate(LocalDateTime.now());

            Deposit updatedDeposit = depositRepository.save(deposit);
            DepositDTO result = convertToDTO(updatedDeposit);

            log.info("[DepositService] withdrawMoney END - depositId: {}, newBalance: {}", result.getDepositId(),
                    newBalance);
            return result;
        }

        log.error("[DepositService] withdrawMoney END - Deposit not found with id: {}", depositId);
        throw new RuntimeException("예금을 찾을 수 없습니다: " + depositId);
    }

    /**
     * Entity를 DTO로 변환
     */
    private DepositDTO convertToDTO(Deposit deposit) {
        return DepositDTO.builder()
                .depositId(deposit.getDepositId())
                .accountNumber(deposit.getAccountNumber())
                .bankCode(deposit.getBankCode())
                .branchCode(deposit.getBranchCode())
                .cifNo(deposit.getCifNo())
                .cifName(deposit.getCifName())
                .depositType(deposit.getDepositType())
                .amount(deposit.getAmount())
                .balance(deposit.getBalance())
                .currency(deposit.getCurrency())
                .interestRate(deposit.getInterestRate())
                .maturityDate(deposit.getMaturityDate())
                .openDate(deposit.getOpenDate())
                .registerDate(deposit.getRegisterDate())
                .registerTime(deposit.getRegisterTime())
                .registerBy(deposit.getRegisterBy())
                .status(deposit.getStatus())
                .description(deposit.getDescription())
                .createdDate(deposit.getCreatedDate())
                .modifiedDate(deposit.getModifiedDate())
                .version(deposit.getVersion())
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
        deposit.setBankCode(depositDTO.getBankCode());
        deposit.setBranchCode(depositDTO.getBranchCode());
        deposit.setCifNo(depositDTO.getCifNo());
        deposit.setCifName(depositDTO.getCifName());
        deposit.setDepositType(depositDTO.getDepositType());
        deposit.setAmount(depositDTO.getAmount());
        deposit.setBalance(depositDTO.getBalance());
        deposit.setCurrency(depositDTO.getCurrency());
        deposit.setInterestRate(depositDTO.getInterestRate());
        deposit.setMaturityDate(depositDTO.getMaturityDate());
        deposit.setOpenDate(depositDTO.getOpenDate());
        deposit.setRegisterDate(depositDTO.getRegisterDate());
        deposit.setRegisterTime(depositDTO.getRegisterTime());
        deposit.setRegisterBy(depositDTO.getRegisterBy());
        deposit.setStatus(depositDTO.getStatus());
        deposit.setDescription(depositDTO.getDescription());
        deposit.setCreatedDate(depositDTO.getCreatedDate());
        deposit.setModifiedDate(depositDTO.getModifiedDate());
        deposit.setVersion(depositDTO.getVersion());
        deposit.setCreatedBy(depositDTO.getCreatedBy());
        deposit.setModifiedBy(depositDTO.getModifiedBy());
        deposit.setIsDeleted(depositDTO.getIsDeleted());
        return deposit;
    }
}