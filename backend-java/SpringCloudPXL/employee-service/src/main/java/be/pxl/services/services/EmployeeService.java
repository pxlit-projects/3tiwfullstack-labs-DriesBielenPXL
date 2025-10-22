package be.pxl.services.services;


import be.pxl.services.client.NotificationClient;
import be.pxl.services.domain.Employee;
import be.pxl.services.model.NotificationRequest;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    private final NotificationClient notificationClient;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);

        NotificationRequest notificationRequest = NotificationRequest.builder().message("Employee created").sender("Dries").build();

        notificationClient.sendNotification(notificationRequest);

        return savedEmployee;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Employee> getEmployeesByOrganization(Long organizationId) {
        return employeeRepository.findByOrganizationId(organizationId);
    }
}
