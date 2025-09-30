package be.pxl.services.controller;

import be.pxl.services.domain.Department;
import be.pxl.services.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

	private final IDepartmentService departmentService;

	@PostMapping("/")
	public ResponseEntity<?> addDepartment(@RequestBody Department department) {
		Department saved = departmentService.addDepartment(department);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
		Department department = departmentService.getDepartmentById(id);
		if (department != null) {
			return new ResponseEntity<>(department, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllDepartments() {
		return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
	}

	@GetMapping("/organization/{organizationId}")
	public ResponseEntity<?> getDepartmentsByOrganization(@PathVariable Long organizationId) {
		return new ResponseEntity<>(departmentService.getDepartmentsByOrganization(organizationId), HttpStatus.OK);
	}

	@GetMapping("/organization/{organizationId}/with-employees")
	public ResponseEntity<?> getDepartmentsByOrganizationWithEmployees(@PathVariable Long organizationId) {
		return new ResponseEntity<>(departmentService.getDepartmentsByOrganizationWithEmployees(organizationId), HttpStatus.OK);
	}
}
