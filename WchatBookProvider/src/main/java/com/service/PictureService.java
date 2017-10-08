package com.service;

import com.entity.Picture;
import com.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
public interface PictureService {
    boolean add(Picture picture);
    boolean remove(Picture picture);
    boolean update(Picture picture);
    Long upload(MultipartFile file, User user) throws IOException;
    Picture queryByParam(Picture picture);
    List<Picture> pageByParam(Picture picture);
}
