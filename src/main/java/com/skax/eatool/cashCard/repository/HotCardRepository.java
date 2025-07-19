package com.skax.eatool.cashCard.repository;

import com.skax.eatool.cashCard.business.cashCard.entity.HotCard;
import com.skax.eatool.cashCard.business.cashCard.entity.HotCardPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("cashCardHotCardRepository")
public interface HotCardRepository extends JpaRepository<HotCard, HotCardPK> {

    /**
     * Find hot card by primary key
     */
    Optional<HotCard> findById(HotCardPK primaryKey);

    /**
     * Find hot cards by card number
     */
    @Query("SELECT h FROM HotCardBusiness h WHERE h.cardNumber = :cardNumber")
    List<HotCard> findByCardNumber(@Param("cardNumber") String cardNumber);

    /**
     * Find hot cards by status
     */
    @Query("SELECT h FROM HotCardBusiness h WHERE h.status = :status")
    List<HotCard> findByStatus(@Param("status") String status);
}
