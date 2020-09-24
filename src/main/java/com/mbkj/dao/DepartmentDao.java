package com.mbkj.dao;

import com.mbkj.entity.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> selectAll();
}
