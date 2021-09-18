package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Company;
import uz.pdp.model.CompanyDto;
import uz.pdp.model.resp.ApiResponse;

import java.util.List;

public interface CompanyService {

    ResponseEntity<ApiResponse<List<Company>>> getAll();

    ResponseEntity<ApiResponse<Company>> get(Long id);

    ResponseEntity<ApiResponse<Company>> create(CompanyDto dto);

    ResponseEntity<ApiResponse<Company>> update(Long id, CompanyDto dto);

    ResponseEntity<ApiResponse<Company>> delete(Long id);
}
