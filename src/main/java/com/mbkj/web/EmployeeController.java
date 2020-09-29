package com.mbkj.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mbkj.entity.Employee;
import com.mbkj.service.EmployeeService;
import com.mbkj.utils.Const;
import com.mbkj.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping(value = "/emps")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 查找全部数据
     *
     * @param pageStart 起始页参数
     * @return
     */
    //@RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @GetMapping("/getAll")
    @ResponseBody
    public ResponseData getAllEmps(@RequestParam(defaultValue = "1", value = "pageStart") Integer pageStart) {

        PageHelper.startPage(pageStart, Const.PAGE_SIZE);

        List<Employee> employees = employeeService.selectAll();

        PageInfo<Employee> pageInfo = new PageInfo<>(employees);

        return ResponseData.success(pageInfo);
    }

    /**
     * 2.增加员工
     * @param employee
     * @return
     */

    //@RequestMapping(value = "/add", method = RequestMethod.POST)
    @PostMapping
    @ResponseBody
    public ResponseData addEmps(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);

        System.out.println(employee);

        return ResponseData.success("保存成功", employee);
    }

    /**
     * 根据ID值查询单个员工
     * @param empId
     * @return
     */

    //@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping("/getOne/{id}")
    @ResponseBody
    public ResponseData selectEmpsById(@PathVariable("id") Integer empId) {
        Employee employee = employeeService.selectEmpById(empId);
        return ResponseData.success(employee);
    }

    /**
     * 修改员工
     * @param employee
     * @return
     */

    //@RequestMapping(value = "/update", method = RequestMethod.POST)
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseData updateEmployee(@PathVariable String id,@RequestBody Employee employee) {
        employeeService.updateEmployee(id,employee);
        return ResponseData.success("更新成功！", employee);
    }

    /**
     * 删除员工
     * @param empId
     * @return
     */

    //@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseData deleteEmployee(@PathVariable("id") Integer empId) {
        employeeService.deleteEmpById(empId);
        return ResponseData.success();
    }


    //@RequestMapping(value = "/delCheck", method = RequestMethod.POST)
    @PostMapping("/delCheck")
    @ResponseBody
    public ResponseData delCheckEmployee(@RequestBody String[] delEmpIds) {

        employeeService.deleteEmpAll(delEmpIds);

        return ResponseData.success(delEmpIds);
    }

}
