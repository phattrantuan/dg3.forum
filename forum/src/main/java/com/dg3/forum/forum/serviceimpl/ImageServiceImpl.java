package com.dg3.forum.forum.serviceimpl;

import com.dg3.forum.forum.entity.Image;
import com.dg3.forum.forum.repository.ImageRepository;
import com.dg3.forum.forum.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image createImagePosts(Image image) {
        image.setComment_pk(null);
        image.setImage_comment(null);
        image.setEnable_image(true);

        return imageRepository.save(image);
    }

    @Override
    public Image createImageComment(Image image) {
        image.setThread_pk(null);
        image.setImage_thread(null);
        image.setEnable_image(true);

        return imageRepository.save(image);
    }

    @Override
    public List<Image> listAllImagePosts(Long thread_pk) {
        return imageRepository.findByThread_pk(thread_pk);
    }
}
