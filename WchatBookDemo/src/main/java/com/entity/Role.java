package com.entity;

import java.util.Set;

/**
 *
 * 角色
 *
 */
public class Role extends AbstractEntity {

    /**
     *
     * 唯一码，是英文
     *
     */
    private String code;
    
    /**
     *
     * 中文名称，也是唯一的
     *
     */
    private String name;
    
    private String description;

    private Integer type;

    /**
     * 权限
     */
    private Set<Permission> permission;

    private Long parentId;

    /**
     * 是否过期
     */
    private Boolean isLeaf;
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Set<Permission> getPermission() {
        return permission;
    }

    public void setPermission(Set<Permission> permission) {
        this.permission = permission;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    
}
