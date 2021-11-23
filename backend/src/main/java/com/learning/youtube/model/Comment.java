package com.learning.youtube.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(value = "Comment")
public class Comment {
    @Id
    private String id;
    private String text;
    private String authorId;
    private AtomicInteger likeCount = new AtomicInteger(0);
    private AtomicInteger dislikeCount = new AtomicInteger(0);

}
