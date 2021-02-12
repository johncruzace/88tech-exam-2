package johncruz.tech.exam.minibank.repository;

import johncruz.tech.exam.minibank.model.domain.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {
}
