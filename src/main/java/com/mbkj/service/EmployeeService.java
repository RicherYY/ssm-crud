package com.mbkj.service;

import com.mbkj.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> selectAll();

    int addEmployee(Employee employee);

    Employee selectEmpById(int empId);

    int updateEmployee(Employee employee);

    int deleteEmpById(int empId);

}
