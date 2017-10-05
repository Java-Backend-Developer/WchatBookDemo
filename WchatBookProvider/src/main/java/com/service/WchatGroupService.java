package com.service;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface WchatGroupService {
    Object get() throws Exception;
    Object create(String name) throws Exception;
    Object update(int id, String name) throws Exception;
    Object getId(String openID) throws Exception;
    Object updateMembers(String openID, int groupID) throws Exception;
}
