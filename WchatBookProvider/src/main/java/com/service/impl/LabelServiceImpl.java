package com.service.impl;

import com.dao.LabelDAO;
import com.entity.Label;
import com.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */
@Service("labelService")
@Transactional
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelDAO labelDAO;


    @Override
    public boolean add(Label label) {
        return labelDAO.saveResultBoolean(label);
    }

    @Override
    public boolean remove(Label label) {
        label = labelDAO.queryByParam(label);
        return labelDAO.remove(label.getId());
    }

    @Override
    public boolean update(Label label) {
        Label l = labelDAO.queryByParam(label);
        if(label.getCode()!=null){
            l.setCode(label.getCode());
        }
        if(label.getName()!=null){
            l.setName(label.getName());
        }
        if(label.getDescription()!=null){
            l.setDescription(label.getDescription());
        }
        if(label.getInvalid()!=null){
            l.setInvalid(label.getInvalid());
        }
        return labelDAO.update(l);
    }

    @Override
    public Label queryByParam(Label label) {
        return labelDAO.queryByParam(label);
    }

    @Override
    public List<Label> pageByParam(Label label) {
        return labelDAO.pageByParam(label);
    }
}
