package com.skax.eatool.user.service.port.file.service.port;

import com.skax.eatool.user.service.port.file.domain.FileModel;

import java.util.List;

public interface FileRepositoryPort {
    FileModel save(FileModel fileModel);
    FileModel findById(Long id);
    List<FileModel> findAll();
    void deleteById(Long id);
}
