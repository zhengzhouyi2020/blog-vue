package com.zzy.service.impl;

import com.zzy.entity.SysCategory;
import com.zzy.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;
    @Test
    public void add() {
        SysCategory sysCategory=new SysCategory();
        sysCategory.setName("随笔");
        categoryService.add(sysCategory);
    }
}