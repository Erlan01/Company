package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Address;
import uz.pdp.model.AddressDto;
import uz.pdp.model.resp.ApiResponse;

import java.util.List;

public interface AddressService {

    ResponseEntity<ApiResponse<Address>> get(Long id);

    ResponseEntity<ApiResponse<List<Address>>> getAll();

    ResponseEntity<ApiResponse<Address>> add(AddressDto dto);

    ResponseEntity<ApiResponse<Address>> update(Long id, AddressDto dto);

    ResponseEntity<ApiResponse<Address>> delete(Long id);
}
