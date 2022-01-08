package com.learning.youtube.service;

import com.learning.youtube.dto.VideoDto;
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

    public VideoDto editVideoMetaData(VideoDto videoDto) {

        Video savedVideo = getVideoById(videoDto.getId());
        // Map the video fields to video
        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setTags(videoDto.getTags());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());

        //save the video to database
        videoRepository.save(savedVideo);
        return videoDto;

    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        Video savedVideo = getVideoById(videoId);
        String thumbnailUrl = fileService.uploadVideo(file);
        savedVideo.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(savedVideo);
        return thumbnailUrl;

    }


    public Video getVideoById(String videoId) {
        //find the video
        return videoRepository.findById(videoId).orElseThrow(() -> new IllegalArgumentException("Cannot find the video id " + videoId));

    }
}
