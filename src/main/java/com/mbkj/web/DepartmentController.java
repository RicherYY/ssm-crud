package com.mbkj.web;

import com.mbkj.entity.Department;
import com.mbkj.service.DepartmentService;
import com.mbkj.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping(value = "/depts")
    @ResponseBody
    public ResponseData getDepts(){
        List<Department> departments = departmentService.selectAll();
        return ResponseData.success("查询成功！",departments);
    }
}
