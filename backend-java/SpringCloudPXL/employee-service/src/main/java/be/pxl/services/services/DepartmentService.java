package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

	private final DepartmentRepository departmentRepository;

	@Override
	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public Department getDepartmentById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public List<Department> getDepartmentsByOrganization(Long organizationId) {
		// Assuming a method exists or will be added to DepartmentRepository
		return departmentRepository.findByOrganizationId(organizationId);
	}

	@Override
	public List<Department> getDepartmentsByOrganizationWithEmployees(Long organizationId) {
		// For now, same as getDepartmentsByOrganization
		return getDepartmentsByOrganization(organizationId);
	}
}
