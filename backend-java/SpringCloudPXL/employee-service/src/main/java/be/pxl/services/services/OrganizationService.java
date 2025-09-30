package be.pxl.services.services;

import be.pxl.services.domain.Organization;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {

	// Add repository and logic here as needed

	@Override
	public Organization getOrganizationById(Long id) {
		return null;
	}

	@Override
	public Organization getOrganizationByIdWithDepartments(Long id) {
		return null;
	}

	@Override
	public Organization getOrganizationByIdWithDepartmentsAndEmployees(Long id) {
		return null;
	}

	@Override
	public Organization getOrganizationByIdWithEmployees(Long id) {
		return null;
	}
}
