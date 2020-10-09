package service.impl;

import dao.BrandDao;
import dao.impl.BrandDaoImpl;
import entity.Brand;
import entity.Label;
import service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    private BrandDao brandDao=new BrandDaoImpl();
    @Override
    public List<Brand> findAll() {
        List<Brand> list = brandDao.findAll();
        return list;
    }
}
