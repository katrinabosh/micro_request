package ru.test.request.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Request {

    private final UUID id;
    private final UUID personId;
    private final String stockCode;
    private final int stockCount;
    private final LocalDateTime requestDate;


    public Request(UUID id, UUID personId, String stockCode, int stockCount, LocalDateTime requestDate) {
        this.id = id;
        this.personId = personId;
        this.stockCode = stockCode;
        this.stockCount = stockCount;
        this.requestDate = requestDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPersonId() {
        return personId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public int getStockCount() {
        return stockCount;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }


}