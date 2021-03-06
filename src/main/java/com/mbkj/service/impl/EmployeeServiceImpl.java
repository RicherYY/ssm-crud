package com.mbkj.service.impl;

import com.mbkj.dao.EmployeeDao;
import com.mbkj.entity.Employee;
import com.mbkj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> selectAll() {
        return employeeDao.selectAll();
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public Employee selectEmpById(int empId) {
        return employeeDao.selectOneByEmpId(empId);
    }

    @Override
    public int updateEmployee(String id,Employee employee) {
        return employeeDao.updateEmpById(Integer.parseInt(id),employee);
    }

    @Override
    public int deleteEmpById(int empId) {
        return employeeDao.deleteEmpById(empId);
    }

    @Override
    public void deleteEmpAll(String[] delEmpIds) {
        for (String delEmpId : delEmpIds) {
            employeeDao.deleteEmpById(Integer.parseInt(delEmpId));
        }
    }
}
