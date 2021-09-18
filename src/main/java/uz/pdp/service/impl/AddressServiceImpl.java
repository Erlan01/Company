package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Address;
import uz.pdp.model.AddressDto;
import uz.pdp.model.resp.ApiResponse;
import uz.pdp.repository.AddressRepo;
import uz.pdp.service.AddressService;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;

    @Autowired
    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }


    @Override
    public ResponseEntity<ApiResponse<Address>> get(Long id) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        return optionalAddress.map(address -> ResponseEntity.ok(new ApiResponse<>(address))).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Address not found"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<List<Address>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(addressRepo.findAll()));
    }

    @Override
    public ResponseEntity<ApiResponse<Address>> add(AddressDto dto) {
        boolean exists = addressRepo.existsByHomeNumber(dto.getHomeNumber());
        if (exists){
            return new ResponseEntity<>(new ApiResponse<>("Home number already exists"), HttpStatus.CONFLICT);
        }
        Address address =Address.builder()
                .homeNumber(dto.getHomeNumber())
                .street(dto.getStreet()).build();
        return ResponseEntity.ok(new ApiResponse<>(addressRepo.save(address)));
    }

    @Override
    public ResponseEntity<ApiResponse<Address>> update(Long id, AddressDto dto) {
        boolean exists = addressRepo.existsByHomeNumber(dto.getHomeNumber());
        if (exists){
            return new ResponseEntity<>(new ApiResponse<>("Home number already exists"), HttpStatus.CONFLICT);
        }
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (!optionalAddress.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>("Address not found"), HttpStatus.NOT_FOUND);
        }
        Address address = optionalAddress.get();
        address.setStreet(dto.getStreet());
        address.setHomeNumber(dto.getHomeNumber());

        return ResponseEntity.ok(new ApiResponse<>(addressRepo.save(address)));
    }

    @Override
    public ResponseEntity<ApiResponse<Address>> delete(Long id) {
        addressRepo.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>("Address deleted"));
    }
}
