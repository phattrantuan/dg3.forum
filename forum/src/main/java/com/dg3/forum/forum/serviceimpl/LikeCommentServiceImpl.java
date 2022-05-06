package com.dg3.forum.forum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg3.forum.forum.dto.LikeCommentPostsDTO;
import com.dg3.forum.forum.repository.CommentRepository;
import com.dg3.forum.forum.repository.LikeRepository;
import com.dg3.forum.forum.repository.RepCommentRepository;
import com.dg3.forum.forum.service.LikeCommentService;

@Service
public class LikeCommentServiceImpl implements LikeCommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private RepCommentRepository rep_commentRepository;

    @Override
    public LikeCommentPostsDTO sumCommentAndLike(Long thread_pk) {
        LikeCommentPostsDTO like_comment = new LikeCommentPostsDTO();

        List<String> listInforLike = likeRepository.inforLike_Posts(thread_pk);
        List<Long> listInforComment = commentRepository.sumComment_Posts(thread_pk);

        int sumComment = 0;

        for(int i = 0; i < listInforComment.size(); i++){
            sumComment += Integer.parseInt(rep_commentRepository.sumRep_comment(listInforComment.get(i).longValue()));
        }

        like_comment.setNumberLike(listInforLike.size());
        like_comment.setNumberComment(sumComment + listInforComment.size());
        like_comment.setListNameLike(listInforLike);

        return like_comment;
    }
}
