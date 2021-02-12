package johncruz.tech.exam.minibank.model.domain;

import johncruz.tech.exam.minibank.model.PersistableObject;
import johncruz.tech.exam.minibank.model.enums.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transaction_master")
public class BankTransaction extends PersistableObject {

    @Column
    private String user;

    @Column
    private LocalDateTime requestDateTime;

    @Column
    private BigDecimal requestAmount;

    @Column
    private BigDecimal runningBalance;

    @Column
    private TransactionType transactionType;

    @Column
    private String transferredFrom;
    @Column
    private String transferredTo;

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getTransferredFrom() {
        return transferredFrom;
    }

    public void setTransferredFrom(String transferredFrom) {
        this.transferredFrom = transferredFrom;
    }

    public String getTransferredTo() {
        return transferredTo;
    }

    public void setTransferredTo(String transferredTo) {
        this.transferredTo = transferredTo;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public BigDecimal getRunningBalance() {
        return runningBalance;
    }

    public void setRunningBalance(BigDecimal runningBalance) {
        this.runningBalance = runningBalance;
    }
}
