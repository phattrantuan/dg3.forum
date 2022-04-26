package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.Image;

import java.util.List;

public interface ImageService {
    /*
    * Create image posts
    * */
    Image createImagePosts(Image image);

    /*
    * Create image comment
    * */
    Image createImageComment(Image image);

    /*
    * List information image posts
    * */
    List<Image> listAllImagePosts(Long thread_pk);
}
