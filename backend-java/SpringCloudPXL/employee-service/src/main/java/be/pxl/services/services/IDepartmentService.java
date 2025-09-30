package be.pxl.services.services;

import be.pxl.services.domain.Department;
import java.util.List;

public interface IDepartmentService {
	Department addDepartment(Department department);
	Department getDepartmentById(Long id);
	List<Department> getAllDepartments();
	List<Department> getDepartmentsByOrganization(Long organizationId);
	List<Department> getDepartmentsByOrganizationWithEmployees(Long organizationId);
}
