package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Address;
import uz.pdp.entity.Company;
import uz.pdp.model.CompanyDto;
import uz.pdp.model.resp.ApiResponse;
import uz.pdp.repository.AddressRepo;
import uz.pdp.repository.CompanyRepo;
import uz.pdp.service.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo companyRepo;
    private final AddressRepo addressRepo;

    @Autowired
    public CompanyServiceImpl(CompanyRepo companyRepo, AddressRepo addressRepo) {
        this.companyRepo = companyRepo;
        this.addressRepo = addressRepo;
    }

    @Override
    public ResponseEntity<ApiResponse<List<Company>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(companyRepo.findAll()));
    }

    @Override
    public ResponseEntity<ApiResponse<Company>> get(Long id) {
        Optional<Company> optionalCompany = companyRepo.findById(id);
        return optionalCompany.map(company -> ResponseEntity.ok(new ApiResponse<>(company))).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Company not found"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<Company>> create(CompanyDto dto) {
        if (companyRepo.existsByCorpName(dto.getCorpName())){
            return new ResponseEntity<>(new ApiResponse<>("CorpName already exists"), HttpStatus.CONFLICT);
        }
        Optional<Address> optionalAddress = addressRepo.findById(dto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>("Address not found"), HttpStatus.NOT_FOUND);
        }
        Company company = Company.builder()
                .corpName(dto.getCorpName())
                .directorName(dto.getDirectorName())
                .address(optionalAddress.get()).build();
        return ResponseEntity.ok(new ApiResponse<>(companyRepo.save(company)));
    }

    @Override
    public ResponseEntity<ApiResponse<Company>> update(Long id, CompanyDto dto) {
        Optional<Company> optionalCompany = companyRepo.findById(id);
        if (!optionalCompany.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>("Company not found"), HttpStatus.NOT_FOUND);
        }
        Optional<Address> optionalAddress = addressRepo.findById(dto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>("Address not found"), HttpStatus.NOT_FOUND);
        }
        if (companyRepo.existsByCorpName(dto.getCorpName())){
            return new ResponseEntity<>(new ApiResponse<>("CorpName already exists"), HttpStatus.CONFLICT);
        }
        Company company = optionalCompany.get();
        company.setCorpName(dto.getCorpName());
        company.setDirectorName(dto.getDirectorName() != null ? dto.getDirectorName() : company.getDirectorName());
        company.setAddress(optionalAddress.get());
        return ResponseEntity.ok(new ApiResponse<>(companyRepo.save(company)));
    }

    @Override
    public ResponseEntity<ApiResponse<Company>> delete(Long id) {
        Optional<Company> optionalCompany = companyRepo.findById(id);
        if (!optionalCompany.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>("Company not found"), HttpStatus.NOT_FOUND);
        }
        companyRepo.delete(optionalCompany.get());
        return new ResponseEntity<>(new ApiResponse<>("Company deleted"), HttpStatus.OK);
    }
}
