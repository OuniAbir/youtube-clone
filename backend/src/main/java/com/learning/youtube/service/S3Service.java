package com.learning.youtube.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service implements FileService {

    public static final String PROJECTYOUTUBECLONE = "projectyoutubeclone";
    private final AmazonS3Client amazonS3Client;

    @Override
    public String uploadVideo(MultipartFile file) {
//        upload the file to AWS
//        prepare a key

        var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID().toString() + "." + filenameExtension;
        var metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try {
            amazonS3Client.putObject(PROJECTYOUTUBECLONE, key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An exception occured while uploading file");
        }
//        video will be visible to angular client
        amazonS3Client.setObjectAcl(PROJECTYOUTUBECLONE, key, CannedAccessControlList.PublicRead);
        return amazonS3Client.getResourceUrl(PROJECTYOUTUBECLONE, key);

    }
}
