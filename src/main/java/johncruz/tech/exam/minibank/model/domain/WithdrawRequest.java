package johncruz.tech.exam.minibank.model.domain;

import johncruz.tech.exam.minibank.model.parentclass.RequestEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "withdraw_requests")
public class WithdrawRequest extends RequestEntity {



}
