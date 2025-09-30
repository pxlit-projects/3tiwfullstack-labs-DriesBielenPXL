package be.pxl.services.services;


import be.pxl.services.domain.Employee;

import java.util.List;

public interface IEmployeeService {


    List<Employee> getAllEmployees();

    Employee addEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> getEmployeesByDepartment(Long departmentId);

    List<Employee> getEmployeesByOrganization(Long organizationId);

}
