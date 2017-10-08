package com.dao;

import com.entity.Picture;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
public interface PictureDAO extends IDAO{
    Picture queryByParam(Picture picture);
    List<Picture> pageByParam(Picture picture);
}
