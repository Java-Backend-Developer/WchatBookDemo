package com.entity;


import java.sql.Timestamp;

public abstract class AbstractEntity {

    private Long id;

    /**
     * 
     * 该记录是否无效： true 有效 false 无效 默认值为true 方法中实现
     * 
     */
    private Boolean invalid = true;

    /**
     * 默认值为1 方法中实现
     */
    private Integer status ;

    private Timestamp createdDatetime;

    /**
     * 
     * 最近修改时间
     * 
     */
    private Timestamp updatedDatetime=new Timestamp(System.currentTimeMillis());

    /**
     * 
     * 排序标签 默认值1 方法中实现
     * 
     */
    private Integer orderTag ;

    private User user;

    /**
     * 每页显示记录数
     */
    private Integer pageSize = 10;

    /**
     * 当前记录下标，从0开始
     */
    private Integer start=1;

    /**
     * 排序方向
     */
    private String sortOrder;

    /**
     * 排序字段
     */
    private String sortField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public Integer getOrderTag() {
        return orderTag;
    }

    public void setOrderTag(Integer orderTag) {
        this.orderTag = orderTag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public void setRows(Integer rows){
        this.pageSize = rows;
    }
    
    public void setPage(Integer page){
        this.start = page;
    }

    public Integer getStart() {
        if(null!=start && null!=pageSize){
            return (start-1)*pageSize;
        }
        return null;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
    
    public Integer getPageNo(){
        return start;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    public void setOrder(String order){
        this.sortOrder = order;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
    
    public void setSort(String sort){
        this.sortField = sort;
    }

    public Integer getCurrentPage() {
        if(null!=start){
            return (start == 0) ? 1 : start + 1;
        }
        return null;
    }
    public void setPageNo(Integer pageNo){
        this.start=pageNo;
    }
}
