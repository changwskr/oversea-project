package com.skcc.oversea.eplatonframework.business.repository;

import com.skcc.oversea.eplatonframework.business.entity.Common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPcommonRepository extends JpaRepository<Common, Long> {
    // TODO: Define repository methods here
}