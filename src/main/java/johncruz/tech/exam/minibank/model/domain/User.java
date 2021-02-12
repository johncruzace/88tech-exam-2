package johncruz.tech.exam.minibank.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import johncruz.tech.exam.minibank.model.PersistableObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="bank_user")
public class User extends PersistableObject {

    @NotNull
    private String userName;

    private String accountName;

    @JsonIgnore
    private String password;

    @OneToOne(fetch = FetchType.EAGER,
            mappedBy = "user")
    @JsonManagedReference(value = "user-accountdetails")
    private AccountDetails accountDetails;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    private String role;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public User() {
        this.setCreatedDate();
    }

    public User(String userName, String accountName, String password, BigDecimal balance, boolean active, String role) {
        this.userName = userName;
        this.accountName = accountName;
        this.password = password;
        this.active = active;
        this.role = role;
        this.setCreatedDate();
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public User(String userName, String accountName, String password, AccountDetails accountDetails, Boolean active, String role) {
        this.userName = userName;
        this.accountName = accountName;
        this.password = password;
        this.accountDetails = accountDetails;
        this.active = active;
        this.role = role;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public User(String userName, String password, BigDecimal balance) {
        this.userName = userName;
        this.password = password;
        this.setCreatedDate();
    }
}
