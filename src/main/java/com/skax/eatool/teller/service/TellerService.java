package com.skax.eatool.teller.service;

import com.skax.eatool.teller.dto.TellerDTO;
import com.skax.eatool.teller.entity.Teller;
import com.skax.eatool.teller.repository.TellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 텔러 관리
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TellerService {

    private final TellerRepository tellerRepository;

    /**
     * 모든 텔러 조회
     */
    public List<TellerDTO> getAllTellers() {
        log.info("[TellerService] getAllTellers START");

        List<TellerDTO> result = tellerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[TellerService] getAllTellers END - count: {}", result.size());
        return result;
    }

    /**
     * ID로 텔러 조회
     */
    public Optional<TellerDTO> getTellerById(Long id) {
        log.info("[TellerService] getTellerById START - id: {}", id);

        Optional<TellerDTO> result = tellerRepository.findById(id)
                .map(this::convertToDTO);

        log.info("[TellerService] getTellerById END - found: {}", result.isPresent());
        return result;
    }

    /**
     * 텔러 ID로 조회
     */
    public Optional<TellerDTO> getTellerByTellerId(String tellerId) {
        log.info("[TellerService] getTellerByTellerId START - tellerId: {}", tellerId);

        Optional<TellerDTO> result = tellerRepository.findByTellerId(tellerId)
                .map(this::convertToDTO);

        log.info("[TellerService] getTellerByTellerId END - found: {}", result.isPresent());
        return result;
    }

    /**
     * 지점코드별 텔러 조회
     */
    public List<TellerDTO> getTellersByBranchCode(String branchCode) {
        log.info("[TellerService] getTellersByBranchCode START - branchCode: {}", branchCode);

        List<TellerDTO> result = tellerRepository.findByBranchCode(branchCode)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[TellerService] getTellersByBranchCode END - count: {}", result.size());
        return result;
    }

    /**
     * 활성 텔러 조회
     */
    public List<TellerDTO> getActiveTellers() {
        log.info("[TellerService] getActiveTellers START");

        List<TellerDTO> result = tellerRepository.findByStatus("ACTIVE")
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("[TellerService] getActiveTellers END - count: {}", result.size());
        return result;
    }

    /**
     * 텔러 등록
     */
    @Transactional
    public TellerDTO createTeller(TellerDTO tellerDTO) {
        log.info("[TellerService] createTeller START - tellerId: {}", tellerDTO.getTellerId());

        Teller teller = convertToEntity(tellerDTO);
        teller.setCreatedDate(LocalDateTime.now());
        teller.setUpdatedDate(LocalDateTime.now());

        Teller savedTeller = tellerRepository.save(teller);
        TellerDTO result = convertToDTO(savedTeller);

        log.info("[TellerService] createTeller END - id: {}", result.getId());
        return result;
    }

    /**
     * 텔러 수정
     */
    @Transactional
    public TellerDTO updateTeller(Long id, TellerDTO tellerDTO) {
        log.info("[TellerService] updateTeller START - id: {}", id);

        Optional<Teller> optionalTeller = tellerRepository.findById(id);
        if (optionalTeller.isPresent()) {
            Teller existingTeller = optionalTeller.get();

            // 업데이트할 필드만 수정
            existingTeller.setTellerName(tellerDTO.getTellerName());
            existingTeller.setBranchCode(tellerDTO.getBranchCode());
            existingTeller.setBankCode(tellerDTO.getBankCode());
            existingTeller.setStatus(tellerDTO.getStatus());
            existingTeller.setUpdatedDate(LocalDateTime.now());
            existingTeller.setLastUpdateUserId(tellerDTO.getLastUpdateUserId());

            Teller updatedTeller = tellerRepository.save(existingTeller);
            TellerDTO result = convertToDTO(updatedTeller);

            log.info("[TellerService] updateTeller END - id: {}", result.getId());
            return result;
        }

        log.error("[TellerService] updateTeller END - Teller not found with id: {}", id);
        throw new RuntimeException("Teller not found with id: " + id);
    }

    /**
     * 텔러 삭제
     */
    @Transactional
    public void deleteTeller(Long id) {
        log.info("[TellerService] deleteTeller START - id: {}", id);

        tellerRepository.deleteById(id);

        log.info("[TellerService] deleteTeller END - id: {}", id);
    }

    /**
     * 텔러 상태 변경
     */
    @Transactional
    public TellerDTO updateTellerStatus(Long id, String status) {
        log.info("[TellerService] updateTellerStatus START - id: {}, status: {}", id, status);

        Optional<Teller> optionalTeller = tellerRepository.findById(id);
        if (optionalTeller.isPresent()) {
            Teller teller = optionalTeller.get();
            teller.setStatus(status);
            teller.setUpdatedDate(LocalDateTime.now());

            Teller updatedTeller = tellerRepository.save(teller);
            TellerDTO result = convertToDTO(updatedTeller);

            log.info("[TellerService] updateTellerStatus END - id: {}", result.getId());
            return result;
        }

        log.error("[TellerService] updateTellerStatus END - Teller not found with id: {}", id);
        throw new RuntimeException("Teller not found with id: " + id);
    }

    /**
     * Entity를 DTO로 변환
     */
    private TellerDTO convertToDTO(Teller teller) {
        return TellerDTO.builder()
                .id(teller.getId())
                .tellerId(teller.getTellerId())
                .tellerName(teller.getTellerName())
                .branchCode(teller.getBranchCode())
                .bankCode(teller.getBankCode())
                .status(teller.getStatus())
                .registerDate(teller.getRegisterDate())
                .registerTime(teller.getRegisterTime())
                .registerBy(teller.getRegisterBy())
                .lastUpdateDate(teller.getLastUpdateDate())
                .lastUpdateTime(teller.getLastUpdateTime())
                .lastUpdateUserId(teller.getLastUpdateUserId())
                .createdDate(teller.getCreatedDate())
                .updatedDate(teller.getUpdatedDate())
                .build();
    }

    /**
     * DTO를 Entity로 변환
     */
    private Teller convertToEntity(TellerDTO tellerDTO) {
        Teller teller = new Teller();
        teller.setId(tellerDTO.getId());
        teller.setTellerId(tellerDTO.getTellerId());
        teller.setTellerName(tellerDTO.getTellerName());
        teller.setBranchCode(tellerDTO.getBranchCode());
        teller.setBankCode(tellerDTO.getBankCode());
        teller.setStatus(tellerDTO.getStatus());
        teller.setRegisterDate(tellerDTO.getRegisterDate());
        teller.setRegisterTime(tellerDTO.getRegisterTime());
        teller.setRegisterBy(tellerDTO.getRegisterBy());
        teller.setLastUpdateDate(tellerDTO.getLastUpdateDate());
        teller.setLastUpdateTime(tellerDTO.getLastUpdateTime());
        teller.setLastUpdateUserId(tellerDTO.getLastUpdateUserId());
        teller.setCreatedDate(tellerDTO.getCreatedDate());
        teller.setUpdatedDate(tellerDTO.getUpdatedDate());
        return teller;
    }
}
