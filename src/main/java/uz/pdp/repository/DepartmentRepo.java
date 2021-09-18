package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

    boolean existsByName(String name);
}
