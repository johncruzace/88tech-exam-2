package johncruz.tech.exam.minibank.repository;

import johncruz.tech.exam.minibank.model.domain.DepositRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<DepositRequest,Long> {
}
