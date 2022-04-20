package com.dg3.forum.forum.serviceimpl;

import com.dg3.forum.forum.entity.Like;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.LikeRepository;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.service.LikeService;
import com.dg3.forum.forum.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Override
    public int sumLike_Posts(Long thread_pk){
        int sum = 0;
        sum = likeRepository.sumLike(thread_pk);
        return sum;
    }

    @Override
    public Like createLike_Posts(Long thread_pk, Like like){
        return likeRepository.save(like);
    }

    @Override
    public Like createLike_Comments(Long thread_pk, Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like checkExistLike_Posts(Long thread_pk, Long user_pk){
        return likeRepository.findByThreak_pkAndUser_pk(thread_pk, user_pk);
    }

    @Override
    public void deleteLike_Posts(Long thread_pk, Long user_pk) {
        likeRepository.deleteByLike_Posts(thread_pk, user_pk);
    }
}
