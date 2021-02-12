package johncruz.tech.exam.minibank.model.request;

import java.math.BigDecimal;

public class AccountDetailsRequest {

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
