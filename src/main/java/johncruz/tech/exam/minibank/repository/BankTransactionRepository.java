package johncruz.tech.exam.minibank.repository;

import johncruz.tech.exam.minibank.model.domain.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {

    List<BankTransaction> findAllByUser(String username);
}
