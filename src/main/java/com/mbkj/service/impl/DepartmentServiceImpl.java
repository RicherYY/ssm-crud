package com.mbkj.service.impl;

import com.mbkj.dao.DepartmentDao;
import com.mbkj.entity.Department;
import com.mbkj.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao dao;

    @Override
    public List<Department> selectAll() {
        return dao.selectAll();
    }
}
