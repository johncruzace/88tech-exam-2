package johncruz.tech.exam.minibank.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "deposit_requests")
public class DepositRequest extends BankTransaction {

    public DepositRequest() {
    }}
