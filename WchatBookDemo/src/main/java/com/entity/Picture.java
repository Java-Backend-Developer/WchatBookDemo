package com.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/5.
 */
public class Picture extends AbstractEntity {
    public Picture(){

    }
    public Picture(Long id){
        this.setId(id);
    }
    /**
     *
     * 本地存储路径，是相对路径，不以“/”开头
     *
     */
    private String path;

    /**
     *
     * 云路径，格式 http://xxx.xxx.xxx/xxx.png
     *
     */
    private String url;

    /**
     * 图片的大小
     *
     */
    private Integer width;
    private Integer height;

    /**
     * 图片的原图Id,原图该属性为0
     */
    private Long original;

    /**
     * 用于存储缩略图
     */
    private Map<String,Picture> thumbnailMap=null;
    private Set<Picture> thumbnailSet=null;

    public Set<Picture> getThumbnailSet() {
        return thumbnailSet;
    }
    public void setThumbnailSet(Set<Picture> thumbnailSet) {
        this.thumbnailSet = thumbnailSet;
    }
    public Picture getThumbnail(int widht, int height) {
        if(thumbnailMap==null)
            return null;
        return thumbnailMap.get(getThumbnailString(widht, height));
    }
    public void addThumbnailToMap(Picture thumbnail){
        if(thumbnailMap==null)
            thumbnailMap=new HashMap<String, Picture>();
        thumbnailMap.put(getThumbnailString(thumbnail.getWidth(),thumbnail.getHeight()), thumbnail);

    }
    public void addThumbnailToMap(List<Picture> thumbnailList){
        for(Picture picture:thumbnailList)
            addThumbnailToMap(picture);
    }
    private String getThumbnailString(int width,int height){
        StringBuffer sb=new StringBuffer();
        sb.append(width);
        sb.append("_");
        sb.append(height);
        return sb.toString();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Long getOriginal() {
        return original;
    }

    public void setOriginal(Long original) {
        this.original = original;
    }
}
