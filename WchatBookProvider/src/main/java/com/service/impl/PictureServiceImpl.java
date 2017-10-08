package com.service.impl;

import com.dao.PictureDAO;
import com.dao.UserDAO;
import com.entity.Picture;
import com.entity.User;
import com.helper.GlobEnv;
import com.service.PictureService;
import com.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDAO pictureDAO;
    @Autowired
    private UserDAO userDAO;


    @Override
    public boolean add(Picture picture) {
        return pictureDAO.saveResultBoolean(picture);
    }

    @Override
    public boolean remove(Picture picture) {
        picture = pictureDAO.queryByParam(picture);
        return pictureDAO.remove(picture.getId());
    }

    @Override
    public boolean update(Picture picture) {
        Picture p = pictureDAO.queryByParam(picture);
        if(picture.getPath()!=null){
            p.setPath(picture.getPath());
        }
        if(picture.getUrl()!=null){
            p.setUrl(picture.getUrl());
        }
        if(picture.getStatus()!=null){
            p.setStatus(picture.getStatus());
        }
        return pictureDAO.update(p);
    }

    @Override
    public Long upload(MultipartFile file, User user) throws IOException {
        user = userDAO.queryByParam(user);
        String relaDir = getRelaDirPath(user.getUsername());
        String _dirPath = GlobEnv.get("store.file") + "/" + relaDir;
        String _fileName = new Date().getTime() + (int) (Math.random() * 900)
                + 100 + "."
                + FilenameUtils.getExtension(file.getOriginalFilename());
        Picture picture = new Picture();
        picture.setPath(_dirPath + "/" + _fileName);
        Long id = (Long) pictureDAO.add(picture);
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
                _dirPath + "/" + _fileName));
        return id;
    }

    @Override
    public Picture queryByParam(Picture picture) {
        return pictureDAO.queryByParam(picture);
    }

    @Override
    public List<Picture> pageByParam(Picture picture) {
        return pictureDAO.pageByParam(picture);
    }

    /**
     * 返回相对目录路径 如 2017/10/07
     *
     * @param rDir
     * @return
     */
    private String getRelaDirPath(String rDir) {

        //从StoreController拷贝而来

        String fDir = DateUtil.formatDateTime(new Date(), "yyyy");
        String sDir = DateUtil.formatDateTime(new Date(), "MM");
        String dDir = DateUtil.formatDateTime(new Date(), "DD");

        File rDirPath = new File(GlobEnv.get("file.stor") + "/" + rDir);
        if (!rDirPath.exists()) {
            rDirPath.mkdir();
        }

        File fDirPath = new File(rDirPath.getPath() + "/" + fDir);
        if (!fDirPath.exists()) {
            fDirPath.mkdir();
        }

        File sDirPath = new File(fDirPath.getPath() + "/" + sDir);
        if (!sDirPath.exists()) {
            sDirPath.mkdir();
        }
        File dDirPath = new File(sDirPath.getPath() + "/" + dDir);
        if (!dDirPath.exists()) {
            dDirPath.mkdir();
        }

        return rDir + "/" + fDir + "/" + sDir + "/" + dDir;
    }
}
