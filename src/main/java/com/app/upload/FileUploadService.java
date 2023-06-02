package com.app.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Service
public class FileUploadService {
    @Autowired
    FileUploadLogic fileUploadLogic;
    public String uploadFile(MultipartFile file, String uploadDir) {
        return fileUploadLogic.uploadFile(file, uploadDir);
    }
}
