import com.mbkj.entity.Department;
import com.mbkj.entity.Employee;
import com.mbkj.service.DepartmentService;
import com.mbkj.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
@WebAppConfiguration
public class DepartmentMapperTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testCRUD(){

        Employee employee = employeeService.selectEmpById(379);
        System.out.println(employee);
    }

}
