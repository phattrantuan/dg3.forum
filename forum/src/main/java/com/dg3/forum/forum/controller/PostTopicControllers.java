package com.dg3.forum.forum.controller;

import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.PostTopic;
import com.dg3.forum.forum.service.PostTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/PostTopic")
public class PostTopicControllers {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private PostTopicService postTopicService;

    /**
     * Show all topic
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Message> listAllTopic() {
        LOGGER.error("listAllTopic");
        List<PostTopic> postTopics = postTopicService.listAllTopic();
        return postTopics.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Không thành công!", "Không tìm thấy chủ đề này!", "")
                )
                : ResponseEntity.status(HttpStatus.OK).body(
                new Message("Thành công!", "Chủ đề:", postTopics)
        )
                ;
    }
}
