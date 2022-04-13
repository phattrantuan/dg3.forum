package com.dg3.forum.forum.serviceimpl;

import com.dg3.forum.forum.entity.PostThread;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.PostThreadRepository;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerServiceImpl implements DealerService {
    @Autowired
    private UserstRepository userstRepository;

    @Autowired
    private PostThreadRepository postThreadRepository;

    @Override
    public List<PostThread> listAllPostDealer (String username){
        Users users = userstRepository.findByUsername(username);
        return postThreadRepository.findAllDealer(users.getUser_pk());
    }

    @Override
    public PostThread savePostsDealer (PostThread postThread){
        return postThreadRepository.save(postThread);
    }

    @Override
    public void updatePostsDealer (PostThread postThread){
        String title_thread = postThread.getTitle_thread();
        String content_of_thread = postThread.getContent_of_thread();
        Long post_topic_pk = postThread.getPost_topic_pk();
        boolean enable_post_thread = postThread.isEnable_post_thread();
        Long thread_pk = postThread.getThread_pk();

        postThreadRepository.updateByPostsDealer(title_thread,content_of_thread,post_topic_pk,enable_post_thread,thread_pk);
    }

    @Override
    public void deletePostsDealer (Long thread_pk){
        postThreadRepository.deleteByPostsDealer(thread_pk);
    }

    @Override
    public PostThread checkExistByThread_pk (Long thread_pk){
        return postThreadRepository.existByThread_pk(thread_pk);
    }
}
