package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Department;
import uz.pdp.model.DepartmentDto;
import uz.pdp.model.resp.ApiResponse;

import java.util.List;

public interface DepartmentService {

    ResponseEntity<ApiResponse<List<Department>>> getAll();

    ResponseEntity<ApiResponse<Department>> get(Long id);

    ResponseEntity<ApiResponse<Department>> create(DepartmentDto dto);

    ResponseEntity<ApiResponse<Department>> update(Long id, DepartmentDto dto);

    ResponseEntity<ApiResponse<Department>> delete(Long id);
}
