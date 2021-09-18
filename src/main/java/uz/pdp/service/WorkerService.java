package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Worker;
import uz.pdp.model.WorkerDto;
import uz.pdp.model.resp.ApiResponse;

import java.util.List;

public interface WorkerService {

    ResponseEntity<ApiResponse<List<Worker>>> getAll();

    ResponseEntity<ApiResponse<Worker>> get(Long id);

    ResponseEntity<ApiResponse<Worker>> create(WorkerDto dto);

    ResponseEntity<ApiResponse<Worker>> update(Long id, WorkerDto dto);

    ResponseEntity<ApiResponse<Worker>> delete(Long id);
}
