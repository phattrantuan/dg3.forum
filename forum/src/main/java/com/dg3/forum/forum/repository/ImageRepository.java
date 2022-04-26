package com.dg3.forum.forum.repository;

import com.dg3.forum.forum.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    /*
    * List information posts
    * */
    @Modifying
    @Transactional
    @Query(value = "select * from image where thread_pk = :thread_pk", nativeQuery = true)
    List<Image> findByThread_pk(Long thread_pk);

}
