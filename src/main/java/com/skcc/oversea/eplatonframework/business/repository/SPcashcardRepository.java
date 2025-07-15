package com.skcc.oversea.eplatonframework.business.repository;

import com.skcc.oversea.eplatonframework.business.entity.CashCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPcashcardRepository extends JpaRepository<CashCard, Long> {
    // TODO: Define repository methods here
}