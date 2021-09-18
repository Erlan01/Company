package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    boolean existsByCorpName(String corpName);
}
