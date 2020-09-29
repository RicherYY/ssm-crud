package com.mbkj.service;

import com.mbkj.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> selectAll();

    int addEmployee(Employee employee);

    Employee selectEmpById(int empId);

    int updateEmployee(String id,Employee employee);

    int deleteEmpById(int empId);

    void deleteEmpAll(String[] delEmpIds);
}
