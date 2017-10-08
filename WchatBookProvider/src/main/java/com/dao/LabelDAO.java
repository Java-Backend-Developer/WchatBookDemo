package com.dao;

import com.entity.Label;

import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */
public interface LabelDAO extends IDAO {
    Label queryByParam(Label label);
    List<Label> pageByParam(Label label);
}
