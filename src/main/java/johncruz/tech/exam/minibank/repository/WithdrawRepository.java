package johncruz.tech.exam.minibank.repository;

import johncruz.tech.exam.minibank.model.domain.WithdrawRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<WithdrawRequest,Long> {
}
