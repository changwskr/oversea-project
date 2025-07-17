package com.skcc.oversea.user.service.port.file.domain;

import org.springframework.core.io.Resource;

public record FileDownload(
        String fileName,
        Resource resource
){
}
