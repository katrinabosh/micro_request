package ru.test.request.service;

import org.springframework.stereotype.Component;
import ru.test.request.model.Request;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class CommandSender {

    public void requestCreated(Request request) {

    }

    public void checkAccount(UUID requestId, UUID personId, BigDecimal sum) {
        // todo
    }

    /**
     *
     * @param requestId
     * @param stockCode
     * @param stockCount
     * @param price допустимая цена акции
     */
    public void buyStock(UUID requestId, String stockCode, int stockCount, BigDecimal price) {
        // todo
    }

    public void changeAccount(UUID requestId, UUID personId, BigDecimal sum) {
        // todo
    }


}
