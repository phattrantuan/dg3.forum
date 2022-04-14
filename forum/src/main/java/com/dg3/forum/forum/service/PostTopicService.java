package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.PostTopic;
import com.dg3.forum.forum.entity.Users;

import java.util.List;
import java.util.Optional;
public interface PostTopicService {
    /**
     * Get all Topic
     * @return
     */
    List<PostTopic> listAllTopic();
    /**
     * Find by name topic
     *
     * @param name_topic
     * @return
     */
    List<PostTopic> findByName_topic(String name_topic);
    /**
     * Delete toppic
     *
     * @param id
     */
    void deletePostTopic(Long id);
    /**
     * Save
     *
     * @param postTopic
     * @return
     */
    PostTopic save(PostTopic postTopic);

}
