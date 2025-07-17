package com.skcc.oversea.user.service.port.file.infrastructure.jpa;

import com.skcc.oversea.user.service.port.file.infrastructure.jpa.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepositoryJpa extends JpaRepository<FileEntity, Long> {
}