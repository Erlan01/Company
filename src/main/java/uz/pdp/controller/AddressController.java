package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Address;
import uz.pdp.model.AddressDto;
import uz.pdp.model.resp.ApiResponse;
import uz.pdp.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Address>> get(@PathVariable Long id){
        return addressService.get(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Address>>> getAll(){
        return addressService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Address>> add(@Validated @RequestBody AddressDto dto){
        return addressService.add(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Address>> update(@PathVariable Long id, @Validated @RequestBody AddressDto dto){
        return addressService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Address>> delete(@PathVariable Long id){
        return addressService.delete(id);
    }
}
