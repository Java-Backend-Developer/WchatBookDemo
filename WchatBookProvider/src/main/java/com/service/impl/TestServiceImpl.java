package com.service.impl;

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

    public String say(String name) {
        return "hello";
    }
    public Object test() {
        return test;
    }
}
