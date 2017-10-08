package com.entity;

/**
 * Created by Administrator on 2017/10/8.
 */
public class Attachment extends AbstractEntity {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件地址
     */
    private String filePath;
    /**
     * 文件网络地址
     */
    private String fileUrl;
    /**
     * 下载次数
     */
    private Integer downloadNum;
    /**
     * 文件大小
     */
    private Integer fileSize;

    //文件类型，0：图片 1：文件
    private Integer fileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}
