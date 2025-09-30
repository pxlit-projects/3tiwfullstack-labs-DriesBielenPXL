package be.pxl.services.controller;

import be.pxl.services.domain.Organization;
import be.pxl.services.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final IOrganizationService organizationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable Long id) {
        Organization organization = organizationService.getOrganizationById(id);
        if (organization != null) {
            return new ResponseEntity<>(organization, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/with-departments")
    public ResponseEntity<?> getOrganizationByIdWithDepartments(@PathVariable Long id) {
        return new ResponseEntity<>(organizationService.getOrganizationByIdWithDepartments(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/with-departments-and-employees")
    public ResponseEntity<?> getOrganizationByIdWithDepartmentsAndEmployees(@PathVariable Long id) {
        return new ResponseEntity<>(organizationService.getOrganizationByIdWithDepartmentsAndEmployees(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/with-employees")
    public ResponseEntity<?> getOrganizationByIdWithEmployees(@PathVariable Long id) {
        return new ResponseEntity<>(organizationService.getOrganizationByIdWithEmployees(id), HttpStatus.OK);
    }
}
