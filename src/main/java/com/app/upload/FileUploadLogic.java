package com.app.upload;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class FileUploadLogic {
    @Autowired
    ServletContext servletContext;
    private static final Logger logger = LoggerFactory.getLogger(FileUploadLogic.class);
    public String uploadFile(MultipartFile file, String uploadDir) {
        String absoluteDiskPath = servletContext.getRealPath(uploadDir);
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(absoluteDiskPath).resolve(fileName);
        try {
            file.transferTo(filePath.toFile());
            return extractPathFromFilePath(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractPathFromFilePath(Path filePath) {
        String[] pathElements = filePath.toString().split("/");
        for(String el: pathElements) {
            logger.warn(el);
        }
        int startIndex = -1;
        for (int i = 0; i < pathElements.length; i++) {
            if(pathElements[i].equals("static")) {
                startIndex = i;
                break;
            }
        }
        if(startIndex != -1)
            return String.join("/", Arrays.copyOfRange(pathElements, startIndex, pathElements.length));
        return null;
    }

    public String extractPathFromImgPath(String imgPath) {
        String[] imgPathElements = imgPath.split("/");
        int startIndex = -1;
        for (int i = 0; i < imgPathElements.length; i++) {
            if (imgPathElements[i].equals("static")) {
                startIndex = i;
                break;
            }
        }
        if (startIndex != -1) {
            return String.join("/", Arrays.copyOfRange(imgPathElements, startIndex, imgPathElements.length));
        }
        return null;
    }

}
