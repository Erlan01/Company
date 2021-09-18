package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Company;
import uz.pdp.model.CompanyDto;
import uz.pdp.model.resp.ApiResponse;
import uz.pdp.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Company>>> getAll(){
        return companyService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Company>> get(@PathVariable Long id){
        return companyService.get(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Company>> create(@Validated @RequestBody CompanyDto dto){
        return companyService.create(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Company>> update(@PathVariable Long id, @Validated @RequestBody CompanyDto dto){
        return companyService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Company>> delete(@PathVariable Long id){
        return companyService.delete(id);
    }
}
