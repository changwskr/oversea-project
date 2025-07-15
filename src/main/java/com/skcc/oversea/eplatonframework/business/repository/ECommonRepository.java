package com.skcc.oversea.eplatonframework.business.repository;

import com.skcc.oversea.eplatonframework.business.entity.Common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ECommonRepository extends JpaRepository<Common, String> {
    // TODO: Define repository methods here
}