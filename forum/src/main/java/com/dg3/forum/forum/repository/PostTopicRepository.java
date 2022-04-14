package com.dg3.forum.forum.repository;

import com.dg3.forum.forum.entity.PostTopic;
import com.dg3.forum.forum.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTopicRepository extends JpaRepository<PostTopic, Long> {
    /**
     * Get by Topic name
     * @param name_topic
     * @return
     */
    PostTopic getByName_topic(String name_topic);

    /**
     * Find user information by username
     *
     * @param name_topic
     * @return
     */
    List<PostTopic> findByName_topic(String name_topic);
}
