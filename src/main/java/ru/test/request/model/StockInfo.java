package ru.test.request.model;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

public class StockInfo {

    private final String stockCode;
    private final int stockCount;
    private final BigDecimal price;

    @ConstructorProperties({"stock_code", "stock_count", "price"})
    public StockInfo(String stockCode, int stockCount, BigDecimal price) {
        this.stockCode = stockCode;
        this.stockCount = stockCount;
        this.price = price;
    }

    public String getStockCode() {
        return stockCode;
    }

    public int getStockCount() {
        return stockCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
