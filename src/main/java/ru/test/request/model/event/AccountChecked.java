package ru.test.request.model.event;

import java.math.BigDecimal;
import java.util.UUID;


public class AccountChecked {

    private final UUID requestId;

    private final BigDecimal sum;


    public AccountChecked(UUID requestId, BigDecimal sum) {
        this.requestId = requestId;
        this.sum = sum;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
