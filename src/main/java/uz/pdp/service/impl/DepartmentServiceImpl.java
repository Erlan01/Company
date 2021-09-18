package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Company;
import uz.pdp.entity.Department;
import uz.pdp.model.DepartmentDto;
import uz.pdp.model.resp.ApiResponse;
import uz.pdp.repository.CompanyRepo;
import uz.pdp.repository.DepartmentRepo;
import uz.pdp.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;

    private final CompanyRepo companyRepo;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo, CompanyRepo companyRepo) {
        this.departmentRepo = departmentRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public ResponseEntity<ApiResponse<List<Department>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(departmentRepo.findAll()));
    }

    @Override
    public ResponseEntity<ApiResponse<Department>> get(Long id) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        return optionalDepartment.map(department -> ResponseEntity.ok(new ApiResponse<>(department))).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Department not found"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<Department>> create(DepartmentDto dto) {
        if(departmentRepo.existsByName(dto.getName())){
            return new ResponseEntity<>(new ApiResponse<>("Name already exists"), HttpStatus.CONFLICT);
        }
        Optional<Company> optionalCompany = companyRepo.findById(dto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>("Company not found"), HttpStatus.NOT_FOUND);
        }

        Department department = Department.builder()
                .name(dto.getName())
                .company(optionalCompany.get()).build();
        return ResponseEntity.ok(new ApiResponse<>(departmentRepo.save(department)));
    }

    @Override
    public ResponseEntity<ApiResponse<Department>> update(Long id, DepartmentDto dto) {
        if(departmentRepo.existsByName(dto.getName())){
            return new ResponseEntity<>(new ApiResponse<>("Name already exists"), HttpStatus.CONFLICT);
        }
        Optional<Company> optionalCompany = companyRepo.findById(dto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>("Company not found"), HttpStatus.NOT_FOUND);
        }

        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if (!optionalDepartment.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>("Department not found"), HttpStatus.NOT_FOUND);
        }
        Department department = optionalDepartment.get();
        department.setName(dto.getName());
        department.setCompany(optionalCompany.get());
        return ResponseEntity.ok(new ApiResponse<>(departmentRepo.save(department)));
    }

    @Override
    public ResponseEntity<ApiResponse<Department>> delete(Long id) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if (!optionalDepartment.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>("Department not found"), HttpStatus.NOT_FOUND);
        }
        departmentRepo.delete(optionalDepartment.get());
        return new ResponseEntity<>(new ApiResponse<>("Department deleted"), HttpStatus.OK);
    }
}
