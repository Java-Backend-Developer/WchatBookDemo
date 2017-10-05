package com.dao;

import com.entity.AbstractEntity;
import com.entity.Test;

/**
 * Created by Administrator on 2017/10/5.
 */
public interface TestDAO extends IDAO{

    AbstractEntity get(Long id);

}
