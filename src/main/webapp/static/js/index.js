$(function () {

    /**
     * 公共部分设置
     */
    var totalPage;
    var currentPage;

    show_page(1);

    //****************************1.首页展示部分********************************
    /**
     * 1.显示所有用户
     * @param pageStart
     */
    function show_page(pageStart) {

        $.ajax({
            url: "/emps/getAll",
            data:"pageStart="+pageStart,
            type: "GET",
            dataType: "json",
            contentType:"application/json;charset=UTF-8",
            success: function (result) {
                //1.解析并显示员工数据表
                build_page_table(result);
                //2.解析并显示分页信息
                build_page_info(result);
                //3.解析并显示分页条数据
                build_page_nav(result);
            }
        })
    }

    /**
     * 解析员工数据表
     * @param result
     */
    function build_page_table(result){
        //清空table表格
        $("#emps_table tbody").empty();
        var emps = result.data.list;

        //遍历元素
        //jQuery提供的each函数,
        $.each(emps, function (index, item) {
            var checkBox=$("<td><input type='checkbox' class='check_item'/></td>");
            var empId = $("<td></td>").append(item.empId);
            var empName = $("<td></td>").append(item.empName);
            var gender = $("<td></td>").append(item.gender);
            var email = $("<td></td>").append(item.email);
            var deptName = $("<td></td>").append(item.department.deptName);

            //修改按钮
            var edit_btn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").
            append($("<span></span>").addClass("glyphicon glyphicon-pencil").
            attr("aria-hidden", true)).append("编辑");

            //删除按钮
            var delete_btn = $("<button></button>").addClass("tn btn-danger btn-sm delete_btn").
            append($("<span></span>").addClass("glyphicon glyphicon-trash").
            attr("aria-hidden", true)).append("删除");

            var td_btn = $("<td></td>").append(edit_btn).append(" ").append(delete_btn);
            $("<tr></tr>").append(checkBox).append(empId).append(empName).append(gender).append(email).append(deptName)
                .append(td_btn ).appendTo("#emps_table tbody");
        });
    }

    /**
     * 解析显示分页信息
     * @param result
     */
    function build_page_info(result) {
        $("#page_info").empty();
        $("#page_info").append("当前" + result.data.pageNum + "页,总共" + result.data.pages +
            "页，总共" + result.data.total + "条记录");
        totalPage = result.data.total;
        currentPage=result.data.pageNum;
    }

    /**
     * 解析并显示分页条数据
     * @param result
     */
    function build_page_nav(result){
        $("#page_nav").empty();
        var ul = $("<ul></ul>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href", "#"));
        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href", "#"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));

        //如果没有前一页，前一页和首页就不能点
        if (result.data.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            //首页
            firstPageLi.click(function () {
                show_page(1);
            });
            prePageLi.click(function () {
                show_page(result.data.pageNum - 1);
            });
        }
        if (result.data.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            //构建点击事件
            nextPageLi.click(function () {
                show_page(result.data.pageNum + 1);
            });
            lastPageLi.click(function () {
                show_page(result.data.pages);
            })
        }
        //添加首页和前一页
        ul.append(firstPageLi).append(prePageLi);
        //遍历添加页码
        $.each(result.data.navigatepageNums, function (index, item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
            //如果是当前选中页面，添加active标识
            if (result.data.pageNum == item) {
                numLi.addClass("active");
            }
            //给每个页码添加点击就跳转
            numLi.click(function () {
                show_page(item);
            });
            ul.append(numLi);
        });
        //添加下一页和末页
        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav");
    }

    //***************************2.增加员工部分************************************

    /**
     * 2.1 增加员工模态框
     */
    $("#emp_Add_modal_Btn").click(function () {
        //在弹出模态框之前需要获取部门信息
         getDepts("#departId");

        //清空模态框中的数据
        $("#myModal form")[0].reset();
        //清空表单样式
        $("#myModal form").find("*").removeClass("has-error has-success");
        $("#myModal form").find(".help-block").text("提示信息");

        $('#myModal').modal({
            backdrop : 'static'
        });
    });


    /**
     * 2.2 获取部门信息
     */

    function getDepts(ele) {
        $.ajax({
            url : "/depts",
            type : 'GET',
            dataType: "json",
            success:function (result) {
                //接收到后台传过来的部门信息之后,遍历, 清空并填充ele
                $(ele).empty();
                $.each(result.data,function (index, dept) {
                    $(ele).append(
                        $("<option></option>").append(dept.deptName).attr("value",dept.deptId)
                    );
                });
            }
        });
    }

    /**
     * 2.3 保存按钮取值
     */
    $("#emp_save_button").click(function () {
        var empName = $("#empName_form_input").val();
        var gender =$("input[name=gender]:checked").val();
        var email =$("#email_form_input").val();
        var deptId =$("#departId option:selected").val();

        //2.3.1 发送ajax请求保存用户
        $.ajax({
            url: "/emps/add",
            type: "POST",
            data: JSON.stringify(
                {
                    empName:empName,
                    gender:gender,
                    email:email,
                    departId:deptId
                }),
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            success: function (result) {
                if (result.status == 0){
                    //1.关闭modal框
                    $("#myModal").modal('hide');
                    //2.来到最后一页，显示刚才保存的数据
                    show_page(currentPage);
                }
            }
        });
    });


    /**
     * 3.修改员工信息
     */
    $(document).on("click", ".edit_btn", function () {
        //var empId = $(this).attr("empId");
        var empId= $(this).parent().parent().children("td").eq(1).text();

        //在弹出模态框之前需要获取部门信息
        getDepts("#departId_update");

        //首先获取该员工的数据
        getEmp(empId);

        //弹出修改modal
        $('#editModal').modal({
            backdrop : 'static'
        });
    })

    //回显
    function getEmp(empId) {
        $.ajax({
            url : "/emps/"+empId,
            type: "GET",
            dataType: "json",
            contentType:"application/json;charset=UTF-8",
            success : function (result) {
                $("#empId_update").val(result.data.empId);
                $("#empName_update").val(result.data.empName);
                $("#email_form_input_update").val(result.data.email);
                $("#editModal input[name=gender_update]").val([result.data.gender]);
                $("#departId_update_div select").val([result.data.department.deptId]);
            }
        })
    }

    //单击更新按钮
    $("#emp_update_button").click(function () {
        var empId = $("#empId_update").val();
        var empName = $("#empName_update").val();
        var gender = $("#editModal input[name=gender_update]:checked").val();
        var email = $("#email_form_input_update").val();
        var deptId = $("#departId_update_div option:selected").val();


        //2.3.1 发送ajax请求保存用户
        $.ajax({
            url: "/emps/update",
            type: "POST",
            data: JSON.stringify(
                {
                    empId:empId,
                    empName:empName,
                    gender:gender,
                    email:email,
                    departId:deptId
                }),
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            success: function (result) {
                if (result.status == 0){
                    //1.关闭modal框
                    $("#editModal").modal('hide');
                    //2.来到最后一页，显示刚才保存的数据
                    show_page(currentPage);
                }
            }
        });
    });

    //*****************************4.删除员工******************************
    /*页面中员工记录的末尾的删除按钮 之所以用这种方法, 是因为这个删除按钮的html代码是动态添加的*/
    $(document).on("click", ".delete_btn", function () {
        //每个删除按钮都给它添加了一个empId属性,对应着该员工
        var empId = $(this).parent().parent().children("td").eq(1).text();


        $('#delete_modal').modal({
            backdrop : 'static'
        });

        $("#delete_confirm").click(function () {

            $.ajax({
                url : "/emps/delete/"+empId,
                type : "GET",
                dataType : "json",
                success : function (result) {
                    if (result) {
                        $('#delete_modal').modal('hide');
                        show_page(currentPage);
                    } else {
                        alert("删除失败!");
                    }
                }
            });
        });
    });


    //checkbox 表单表头的选择框
    $("#check_all_page").click(function () {
        //根据 check_all_page 的是否被选中,来设置表单中的check_item的选中状态
        $(".check_item").prop("checked", $(this).prop("checked"));
    });

    //设置check_item的点击事件, 来给check_all_page赋值
    $(document).on("click",".check_item", function () {
        //如果表格中的checkbox都被选中了, 那么表头的checkbox也被选中
        var flag = $(".check_item:checked").length == $(".check_item").length;
        $("#check_all_page").prop("checked",flag);
    });

    //大删除按钮 删除被选中的人员
    $("#emp_delete_selected_btn").click(function () {
        if ($(".check_item:checked").length < 1) {
            alert("请先选择要删除的人员");
            return false;
        }

        //循环遍历每个被选择的checkBox的值
        var select_id = "";
        $.each($(".check_item:checked"),function () {
            select_id += $(this).parents("tr").find("td:eq(1)").text() + "-";
        });

        if (select_id.length > 1)
            select_id = select_id.substring(0, select_id.length - 1);

        /* 路径/emp/{"1-3-4-5"} : DELETE 删除员工id为 1 3 4 5 的员工
        *  返回 true 或 false
        * */

    });
});