package com.learning.youtube.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadVideo(MultipartFile file);
}
