package johncruz.tech.exam.minibank.model.request;

import java.math.BigDecimal;

public class TransactionRequest {

    String usernameToTransfer;
    BigDecimal amount;

    public String getUsernameToTransfer() {
        return usernameToTransfer;
    }

    public void setUsernameToTransfer(String usernameToTransfer) {
        this.usernameToTransfer = usernameToTransfer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
