package vn.alaxed.booksellingweb_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.alaxed.booksellingweb_backend.dao.CategoryRepository;
import vn.alaxed.booksellingweb_backend.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    
    @Override
    public void delCategory(int id) {
    
    
    }
    
}
