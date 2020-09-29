package com.mbkj.dao;

import com.mbkj.entity.Employee;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface EmployeeDao {

    //无条件分页查询,返回的是某一页的list (首页需要)
    List<Employee> selectAll();

    int addEmployee(Employee employee);

    Employee selectOneByEmpId(int empId);

    int updateEmpById(@Param("empId") int id,@Param("emp") Employee employee);

    int deleteEmpById(int empId);

}
