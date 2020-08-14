package ru.test.request.model.event;

import java.math.BigDecimal;
import java.util.UUID;


public class AccountChanged {

    private final UUID requestId;

    private final UUID personId;

    private final BigDecimal sum;


    public AccountChanged(UUID requestId, UUID personId, BigDecimal sum) {
        this.requestId = requestId;
        this.personId = personId;
        this.sum = sum;
    }

    public UUID getPersonId() {
        return personId;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
