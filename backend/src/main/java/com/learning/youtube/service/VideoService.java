package com.learning.youtube.service;

import com.learning.youtube.model.Video;
import com.learning.youtube.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final FileService fileService;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile file) {
        //upload file to AWS S3
        String videoUrl = fileService.uploadVideo(file);

        //Save Video Date to DB
        var video = new Video();
        video.setUrl(videoUrl);
        videoRepository.save(video);

    }
}
