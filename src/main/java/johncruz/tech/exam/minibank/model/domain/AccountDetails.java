package johncruz.tech.exam.minibank.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import johncruz.tech.exam.minibank.model.PersistableObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_details")
public class AccountDetails extends PersistableObject {

    @Column
    private String accountId;

    @Column
    private BigDecimal balance;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user-accountdetails")
    private User user;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountDetails() {
        this.setCreatedDate(LocalDateTime.now());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountDetails(String accountId, BigDecimal balance, User user) {
        this.accountId = accountId;
        this.balance = balance;
        this.user = user;
    }

    public AccountDetails(String accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
}
