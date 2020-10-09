package service.impl;

import dao.LabelDao;
import dao.impl.LabelDaoImpl;
import entity.Label;
import service.LabelService;

import java.util.List;

public class LabelServiceImpl implements LabelService {
    private LabelDao labelDao = new LabelDaoImpl();

    @Override
    public List<Label> findAll() {
        List<Label> list = labelDao.findAll();
        return list;
    }
}
