package com.service;

import com.entity.Label;

import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */
public interface LabelService {
    boolean add(Label label);
    boolean remove(Label label);
    boolean update(Label label);
    Label queryByParam(Label label);
    List<Label> pageByParam(Label label);
}
