package com.dg3.forum.forum.serviceimpl;

import com.dg3.forum.forum.entity.PostTopic;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.PostTopicRepository;

import com.dg3.forum.forum.service.PostTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostTopicServiceImpI implements PostTopicService {

    @Autowired
    PostTopicRepository postTopicRepository;

    @Override
    public List<PostTopic> listAllTopic() {
        return postTopicRepository.findAll();
    }

    @Override
    public List<PostTopic> findByName_topic(String name_topic) {
        return postTopicRepository.findByName_topic(name_topic);
    }

    @Override
    public void deletePostTopic(Long id) {
        postTopicRepository.deleteById(id);
    }

    @Override
    public PostTopic save(PostTopic postTopic) {
        return postTopicRepository.save(postTopic);
    }
}
