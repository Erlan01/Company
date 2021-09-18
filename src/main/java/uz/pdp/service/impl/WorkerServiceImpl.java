package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Address;
import uz.pdp.entity.Department;
import uz.pdp.entity.Worker;
import uz.pdp.model.WorkerDto;
import uz.pdp.model.resp.ApiResponse;
import uz.pdp.repository.AddressRepo;
import uz.pdp.repository.DepartmentRepo;
import uz.pdp.repository.WorkerRepo;
import uz.pdp.service.WorkerService;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepo workerRepo;
    private final AddressRepo addressRepo;
    private final DepartmentRepo departmentRepo;

    @Autowired
    public WorkerServiceImpl(WorkerRepo workerRepo, AddressRepo addressRepo, DepartmentRepo departmentRepo) {
        this.workerRepo = workerRepo;
        this.addressRepo = addressRepo;
        this.departmentRepo = departmentRepo;
    }


    @Override
    public ResponseEntity<ApiResponse<List<Worker>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(workerRepo.findAll()));
    }

    @Override
    public ResponseEntity<ApiResponse<Worker>> get(Long id) {
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        return optionalWorker.map(worker -> ResponseEntity.ok(new ApiResponse<>(worker))).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Department not found"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<Worker>> create(WorkerDto dto) {
        if (workerRepo.existsByPhoneNumber(dto.getPhoneNumber())) {
            return new ResponseEntity<>(new ApiResponse<>("Phone number already exists"), HttpStatus.CONFLICT);
        }
        Optional<Address> optionalAddress = addressRepo.findById(dto.getAddressId());
        if (!optionalAddress.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>("Address not found"), HttpStatus.NOT_FOUND);
        }
        Optional<Department> optionalDepartment = departmentRepo.findById(dto.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>("Department not found"), HttpStatus.NOT_FOUND);
        }
        Worker worker = Worker.builder()
                .address(optionalAddress.get())
                .department(optionalDepartment.get())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber()).build();
        return ResponseEntity.ok(new ApiResponse<>(workerRepo.save(worker)));
    }

    @Override
    public ResponseEntity<ApiResponse<Worker>> update(Long id, WorkerDto dto) {
        if (workerRepo.existsByPhoneNumber(dto.getPhoneNumber())) {
            return new ResponseEntity<>(new ApiResponse<>("Phone number already exists"), HttpStatus.CONFLICT);
        }
        Optional<Address> optionalAddress = addressRepo.findById(dto.getAddressId());
        if (!optionalAddress.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>("Address not found"), HttpStatus.NOT_FOUND);
        }
        Optional<Department> optionalDepartment = departmentRepo.findById(dto.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>("Department not found"), HttpStatus.NOT_FOUND);
        }
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        if (!optionalWorker.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>("Worker not found"), HttpStatus.NOT_FOUND);
        }
        Worker worker = optionalWorker.get();
        worker.setName(dto.getName() != null ? dto.getName() : worker.getName());
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());

        return ResponseEntity.ok(new ApiResponse<>(workerRepo.save(worker)));
    }

    @Override
    public ResponseEntity<ApiResponse<Worker>> delete(Long id) {
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        if (!optionalWorker.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>("Worker not found"), HttpStatus.NOT_FOUND);
        }
        workerRepo.deleteById(id);
        return new ResponseEntity<>(new ApiResponse<>("Worker deleted"), HttpStatus.OK);
    }
}
