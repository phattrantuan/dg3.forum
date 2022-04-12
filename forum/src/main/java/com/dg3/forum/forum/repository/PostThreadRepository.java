package com.dg3.forum.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dg3.forum.forum.entity.PostThread;
@Repository
public interface PostThreadRepository extends JpaRepository<PostThread, Long>{

}
