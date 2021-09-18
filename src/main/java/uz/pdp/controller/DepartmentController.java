package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Department;
import uz.pdp.model.DepartmentDto;
import uz.pdp.model.resp.ApiResponse;
import uz.pdp.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Department>>> getAll(){
        return departmentService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Department>> get(@PathVariable Long id){
        return departmentService.get(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Department>> create(@Validated @RequestBody DepartmentDto dto){
        return departmentService.create(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Department>> update(@PathVariable Long id, @Validated @RequestBody DepartmentDto dto){
        return departmentService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Department>> delete(@PathVariable Long id){
        return departmentService.delete(id);
    }
}
