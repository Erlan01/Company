package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Worker;
import uz.pdp.model.WorkerDto;
import uz.pdp.model.resp.ApiResponse;
import uz.pdp.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Worker>>> getAll(){
        return workerService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Worker>> get(@PathVariable Long id){
        return workerService.get(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Worker>> create(@Validated @RequestBody WorkerDto dto){
        return workerService.create(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Worker>> update(@PathVariable Long id, @Validated @RequestBody WorkerDto dto){
        return workerService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Worker>> delete(@PathVariable Long id){
        return workerService.delete(id);
    }
}
