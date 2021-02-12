package johncruz.tech.exam.minibank.model.domain;

import johncruz.tech.exam.minibank.model.parentclass.RequestEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "deposit_requests")
public class DepositRequest extends RequestEntity {

    public DepositRequest() {
    }
}
