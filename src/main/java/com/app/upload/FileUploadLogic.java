package com.app.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

@Component
public class FileUploadLogic {
    public String uploadFile(MultipartFile file, String uploadDir) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        try {
            Files.copy(file.getInputStream(), filePath);
            return extractPathFromFilePath(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractPathFromFilePath(Path filePath) {
        String[] pathElements = filePath.toString().split("\\\\");
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
