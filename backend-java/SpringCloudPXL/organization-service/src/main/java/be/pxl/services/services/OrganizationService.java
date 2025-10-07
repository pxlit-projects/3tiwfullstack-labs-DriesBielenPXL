package be.pxl.services.services;

import be.pxl.services.domain.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {
	private final be.pxl.services.repository.OrganizationRepository repository;

	@Override
	public Organization getOrganizationById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Organization getOrganizationByIdWithDepartments(Long id) {
		return getOrganizationById(id);
	}

	@Override
	public Organization getOrganizationByIdWithDepartmentsAndEmployees(Long id) {
		return getOrganizationById(id);
	}

	@Override
	public Organization getOrganizationByIdWithEmployees(Long id) {
		return getOrganizationById(id);
	}
}
