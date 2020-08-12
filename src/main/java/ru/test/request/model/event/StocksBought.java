package ru.test.request.model.event;

import java.math.BigDecimal;
import java.util.UUID;

public class StocksBought {

    private final UUID requestId;

    /**
     * Сумма, отданная за акции
     */
    private final BigDecimal sum;

    public StocksBought(UUID requestId, BigDecimal sum) {
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
