package johncruz.tech.exam.minibank.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "withdraw_requests")
public class WithdrawRequest extends BankTransaction {



}
