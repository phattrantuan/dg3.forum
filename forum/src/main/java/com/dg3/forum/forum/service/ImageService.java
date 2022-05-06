package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.Image;

import java.util.List;

public interface ImageService {
    /*
    * Create image posts
    * @param image
    * @return object image
    * */
    Image createImagePosts(Image image);

    /*
    * Create image comment
    * @param image
    * @return object image
    * */
    Image createImageComment(Image image);

    /*
    * List information image posts
    * @param thread_pk
    * @return list object image
    * */
    List<Image> listAllImagePosts(Long thread_pk);
    
    /**
     * List all image Comment
     * @return list image comment
     */
    List<Image> listAllImageComment();
    
    /**
     * delete image comment
     * @param image_pk
     */
    void deleteImageComment(Long image_pk);
    

	/**
	 * check exist id of image
	 * @param image_pk
	 * @return true or false
	 */
	boolean existById(Long image_pk);
    
}
