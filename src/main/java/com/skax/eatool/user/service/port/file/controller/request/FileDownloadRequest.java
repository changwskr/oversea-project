package com.skax.eatool.user.service.port.file.controller.request;

import com.skax.eatool.user.service.port.file.domain.FileModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileDownloadRequest {

    private final long id;
    private final String filePath;

    public long getFileId() {
        return this.id;
    }

    public String getDirPath() {
        return getDirectoryPath(this.filePath);
    }

    public String getOrgName() {
        return getFileName(this.filePath);
    }

    public FileModel toModel() {

        if (this.id <= 0) {
            return FileModel.builder()
                    .dirPath(getDirectoryPath(this.filePath))
                    .orgName(getFileName(this.filePath))
                    .build();
        }

        return FileModel.builder()
                .id(this.id)
                .build();
    }

    private String getFileName(String filePath) {
        // 마지막 구분자 위치를 찾아서 파일명을 추출
        int lastSeparatorIndex = getLastSeparatorIndex(filePath);
        return (lastSeparatorIndex >= 0) ? filePath.substring(lastSeparatorIndex + 1) : filePath;
    }

    private String getDirectoryPath(String filePath) {
        // 마지막 구분자 위치를 찾아서 디렉토리 경로를 추출
        int lastSeparatorIndex = getLastSeparatorIndex(filePath);
        return (lastSeparatorIndex >= 0) ? filePath.substring(0, lastSeparatorIndex) : "";
    }

    private int getLastSeparatorIndex(String filePath) {
        int lastSeparatorIndex = filePath.lastIndexOf('/'); // UNIX 계열
        if (lastSeparatorIndex == -1) {
            lastSeparatorIndex = filePath.lastIndexOf('\\'); // Windows 계열
        }
        return lastSeparatorIndex;
    }
}
