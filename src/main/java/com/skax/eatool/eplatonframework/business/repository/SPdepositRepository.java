package com.skax.eatool.eplatonframework.business.repository;

import com.skax.eatool.eplatonframework.business.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPdepositRepository extends JpaRepository<Deposit, Long> {
    // TODO: Define repository methods here
}
