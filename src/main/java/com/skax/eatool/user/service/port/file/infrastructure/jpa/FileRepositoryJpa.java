package com.skax.eatool.user.service.port.file.infrastructure.jpa;

import com.skax.eatool.user.service.port.file.infrastructure.jpa.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepositoryJpa extends JpaRepository<FileEntity, Long> {
}
