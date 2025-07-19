package com.skax.eatool.common.repository;

import com.skax.eatool.common.entity.Common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("mainCommonRepository")
public interface CommonRepository extends JpaRepository<Common, Long> {
    // Add custom query methods as needed
}
