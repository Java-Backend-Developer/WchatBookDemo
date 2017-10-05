package com.service.impl;

import com.dao.TestDAO;
import com.service.TestService;
import com.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private Test test;
    @Autowired
    private TestDAO testDAO;

    public String say(String name) {
        return "hello";
    }
    public Object test() {
        return test;
    }
    public Object get(Long id){
        Object test = testDAO.get(id);
        return test;
    }
}
