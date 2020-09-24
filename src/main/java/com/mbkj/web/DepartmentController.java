package com.mbkj.web;

import com.mbkj.entity.Department;
import com.mbkj.service.DepartmentService;
import com.mbkj.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getDepts(){
        List<Department> departments = departmentService.selectAll();
        return ResponseData.success("查询成功！",departments);
    }
}
