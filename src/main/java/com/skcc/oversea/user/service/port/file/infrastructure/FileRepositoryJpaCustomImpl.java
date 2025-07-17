package com.skcc.oversea.user.service.port.file.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.skcc.oversea.user.service.port.file.domain.FileModel;
import com.skcc.oversea.user.service.port.file.infrastructure.jpa.FileRepositoryJpa;
import com.skcc.oversea.user.service.port.file.infrastructure.jpa.FileEntity;
import com.skcc.oversea.user.service.port.file.service.port.FileRepositoryPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FileRepositoryJpaCustomImpl implements FileRepositoryPort {

    private final FileRepositoryJpa repository;

    @Override
    public FileModel save(FileModel fileModel) {
        log.info("[FileRepositoryJpaCustomImpl] save START - orgName: {}", fileModel.getOrgName());
        
        try {
            FileEntity savedEntity = repository.save(FileEntity.from(fileModel));
            FileModel result = savedEntity.toModel();
            log.info("[FileRepositoryJpaCustomImpl] save END - id: {}", result.getId());
            return result;
        } catch (Exception e) {
            log.error("[FileRepositoryJpaCustomImpl] save 오류 발생: {}", e.getMessage(), e);
            log.info("[FileRepositoryJpaCustomImpl] save END - 오류");
            throw e;
        }
    }

    @Override
    public FileModel findById(Long id) {
        log.info("[FileRepositoryJpaCustomImpl] findById START - id: {}", id);
        
        try {
            FileEntity entity = repository.findById(id).orElse(null);
            FileModel result = entity == null ? null : entity.toModel();
            log.info("[FileRepositoryJpaCustomImpl] findById END - found: {}", result != null);
            return result;
        } catch (Exception e) {
            log.error("[FileRepositoryJpaCustomImpl] findById 오류 발생: {}", e.getMessage(), e);
            log.info("[FileRepositoryJpaCustomImpl] findById END - 오류");
            throw e;
        }
    }

    @Override
    public List<FileModel> findAll() {
        log.info("[FileRepositoryJpaCustomImpl] findAll START");
        
        try {
            List<FileEntity> entities = repository.findAll();
            List<FileModel> result = entities.stream()
                    .map(FileEntity::toModel)
                    .toList();
            log.info("[FileRepositoryJpaCustomImpl] findAll END - count: {}", result.size());
            return result;
        } catch (Exception e) {
            log.error("[FileRepositoryJpaCustomImpl] findAll 오류 발생: {}", e.getMessage(), e);
            log.info("[FileRepositoryJpaCustomImpl] findAll END - 오류");
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("[FileRepositoryJpaCustomImpl] deleteById START - id: {}", id);
        
        try {
            repository.deleteById(id);
            log.info("[FileRepositoryJpaCustomImpl] deleteById END - 성공");
        } catch (Exception e) {
            log.error("[FileRepositoryJpaCustomImpl] deleteById 오류 발생: {}", e.getMessage(), e);
            log.info("[FileRepositoryJpaCustomImpl] deleteById END - 오류");
            throw e;
        }
    }
}
