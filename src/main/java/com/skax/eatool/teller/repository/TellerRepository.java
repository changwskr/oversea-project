package com.skax.eatool.teller.repository;

import com.skax.eatool.teller.entity.Teller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("mainTellerRepository")
public interface TellerRepository extends JpaRepository<Teller, Long> {

    /**
     * 텔러 ID로 조회
     */
    Optional<Teller> findByTellerId(String tellerId);

    /**
     * 지점코드별 텔러 조회
     */
    List<Teller> findByBranchCode(String branchCode);

    /**
     * 상태별 텔러 조회
     */
    List<Teller> findByStatus(String status);

    /**
     * 텔러 이름으로 조회
     */
    List<Teller> findByTellerNameContainingIgnoreCase(String tellerName);

    /**
     * 텔러 ID로 조회 (부분일치)
     */
    List<Teller> findByTellerIdContainingIgnoreCase(String tellerId);
}
