package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Worker;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Long> {

    boolean existsByPhoneNumber(String phoneNumber);
}
